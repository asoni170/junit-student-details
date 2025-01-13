package com.amit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amit.entity.StudentEntity;
import com.amit.response.StudentResponse;

@Service
public interface StudentService {

	public List<StudentEntity> getStudentsByRollNumbers(List<String> rollNumbers);

	public StudentResponse getStudentDetailsByRollnumbers(List<String> rollNumbers);

	public StudentResponse getStudentDetailsByRollnumbersSingleThread(List<String> rollNumbers);

	public StudentResponse getAllStudentDetails(Integer pageNumber, Integer pageSize);
}
