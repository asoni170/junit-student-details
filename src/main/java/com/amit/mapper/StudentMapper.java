package com.amit.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amit.dto.AddressDto;
import com.amit.dto.CommunicationDto;
import com.amit.entity.AddressEntity;
import com.amit.entity.CommunicationEntity;
import com.amit.entity.StudentEntity;
import com.amit.response.StudentDetails;

@Component
public class StudentMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public List<StudentDetails> map(List<StudentEntity> studentEntityList,
			Map<Integer, List<AddressEntity>> addressMap, Map<Integer, List<CommunicationEntity>> communicationMap) {
		
		var result = studentEntityList.stream().map(student -> {
			
			var addressList = addressMap.getOrDefault(student.getStudentId(), List.of());
			var communicationList = communicationMap.getOrDefault(student.getStudentId(), List.of());
			
			return mapToStudentResponse(student, addressList, communicationList);
		}).toList();
		
		return result;
	}
	
	private StudentDetails mapToStudentResponse(
			StudentEntity student, List<AddressEntity> addressEntityList, List<CommunicationEntity> communicationEntityList) {
		
		var communicationList = new ArrayList<CommunicationDto>();
		var addressList = new ArrayList<AddressDto>();
		
		addressEntityList.stream()
		      .forEach(address -> addressList.add(modelMapper.map(address, AddressDto.class)));
		communicationEntityList.stream()
		      .forEach(communication -> communicationList.add(modelMapper.map(communication, CommunicationDto.class)));
		
		return StudentDetails.builder()
				.studentId(student.getStudentId())
				.studentName(student.getStudentName())
				.rollNumber(student.getRollNumber())
				.communicationList(communicationList)
				.addressList(addressList)
				.build();
	}

}
