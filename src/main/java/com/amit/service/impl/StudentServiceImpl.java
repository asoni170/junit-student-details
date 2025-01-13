package com.amit.service.impl;

import static com.amit.util.PageNumberAndSizeValidator.validatePage;
import static com.amit.util.constant.CommonConstants.PARAMETER_ROLLNUMBER;
import static com.amit.util.constant.CommonConstants.RESOURCE_STUDENT;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amit.dao.StudentDao;
import com.amit.entity.StudentEntity;
import com.amit.exception.ResourceNotFoundException;
import com.amit.mapper.StudentMapper;
import com.amit.response.StudentResponse;
import com.amit.service.StudentService;

@Service
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao;

	@Qualifier("asyncTaskExecutor")
	private AsyncTaskExecutor executor;

	private StudentMapper studentMapper;

	@Override
	public List<StudentEntity> getStudentsByRollNumbers(List<String> rollNumbers) {
		var studentEntityList = studentDao.fetchStudentByRollNumber(rollNumbers);

		if (CollectionUtils.isEmpty(studentEntityList)) {
			throw new ResourceNotFoundException(
					RESOURCE_STUDENT, PARAMETER_ROLLNUMBER, rollNumbers.toString());
		}
		return studentEntityList;
	}

	@Override
	public StudentResponse getStudentDetailsByRollnumbers(List<String> rollNumbers) {
		var studentEntityList = studentDao.fetchStudentByRollNumber(rollNumbers);

		if (CollectionUtils.isEmpty(studentEntityList)) {
			throw new ResourceNotFoundException(
					RESOURCE_STUDENT, PARAMETER_ROLLNUMBER, rollNumbers.toString());
		}
		
		var studentIds = studentEntityList.stream().map(StudentEntity::getStudentId).toList();
		
		var addressListCF = CompletableFuture.supplyAsync(
				() -> studentDao.fetchAddressListByStudentId(studentIds), executor);
		
		var communicationListCF = CompletableFuture.supplyAsync(
				() -> studentDao.fetchCommunicationListByStudentId(studentIds), executor);
		
		CompletableFuture.allOf(addressListCF, communicationListCF).join();
		
		var result = new StudentResponse();
		
		try {
			result.setStudents(studentMapper.map(studentEntityList, addressListCF.get(), communicationListCF.get()));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	@Override
	public StudentResponse getStudentDetailsByRollnumbersSingleThread(List<String> rollNumbers) {
		var studentEntityList = studentDao.fetchStudentByRollNumber(rollNumbers);

		if (CollectionUtils.isEmpty(studentEntityList)) {
			throw new ResourceNotFoundException(
					RESOURCE_STUDENT, PARAMETER_ROLLNUMBER, rollNumbers.toString());
		}
		
		var studentIds = studentEntityList.stream().map(StudentEntity::getStudentId).toList();
		
		var addressList = studentDao.fetchAddressListByStudentId(studentIds);
		var communicationList = studentDao.fetchCommunicationListByStudentId(studentIds);
		
		
		var result = new StudentResponse();
		result.setStudents(studentMapper.map(studentEntityList, addressList, communicationList));
		
		return result;
	}

	@Override
	public StudentResponse getAllStudentDetails(Integer pageNumber, Integer pageSize) {
		var result = new StudentResponse();
		try {
			validatePage(pageNumber, pageSize);
			
			var totalRecordCountCF = CompletableFuture.supplyAsync(() -> studentDao.getTotalRecordCount(), executor);
			var studentEntityCF = CompletableFuture
					.supplyAsync(() -> studentDao.getPaginatedStudent(pageNumber, pageSize), executor);
			var studentEntityList = studentEntityCF.get();
			var studentIds = studentEntityList.stream().map(StudentEntity::getStudentId).toList();
			
			var addressEntityCF = CompletableFuture
					.supplyAsync(() -> studentDao.fetchAddressListByStudentId(studentIds), executor);
			var communicationEntityCF = CompletableFuture
					.supplyAsync(() -> studentDao.fetchCommunicationListByStudentId(studentIds), executor);
			
			CompletableFuture.allOf(totalRecordCountCF, studentEntityCF, addressEntityCF, communicationEntityCF);
			
			result.setStudents(studentMapper.map(studentEntityCF.get(), addressEntityCF.get(), communicationEntityCF.get()));
			result.setTotalRecords(totalRecordCountCF.get());


		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
