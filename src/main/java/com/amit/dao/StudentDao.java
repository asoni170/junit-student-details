package com.amit.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.amit.entity.AddressEntity;
import com.amit.entity.CommunicationEntity;
import com.amit.entity.StudentEntity;

@Repository
public interface StudentDao {
	
	public List<StudentEntity> fetchStudentByRollNumber(List<String> rollNumberList);
	
	public Map<Integer,List<AddressEntity>> fetchAddressListByStudentId(List<Integer> studentIds);
	
	public Map<Integer,List<CommunicationEntity>> fetchCommunicationListByStudentId(List<Integer> studentIds);

	public Integer getTotalRecordCount();

	public List<StudentEntity> getPaginatedStudent(Integer pageNumber, Integer pageSize);
	
}
