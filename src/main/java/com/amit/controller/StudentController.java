package com.amit.controller;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RestController;

import com.amit.controller.api.ApiController;
import com.amit.entity.StudentEntity;
import com.amit.response.StudentResponse;
import com.amit.service.StudentService;

@RestController
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class StudentController implements ApiController {

	private StudentService studentService;

	@Override
	public ResponseEntity<List<StudentEntity>> getStudentListByRollnumber(List<String> rollNumbers) {
		var result = studentService.getStudentsByRollNumbers(rollNumbers);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@Override
	public ResponseEntity<StudentResponse> getStudentDetailsByRollnumber(
			List<String> rollNumbers) {
		StopWatch sw = new StopWatch();
		sw.start();
		var result = studentService.getStudentDetailsByRollnumbers(rollNumbers);
		sw.stop();
		result.setTimeTakenByService(String.valueOf(sw.getTotalTimeMillis()));
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@Override
	public ResponseEntity<StudentResponse> getStudentDetailsByRollnumberSinglethread(
			List<String> rollNumbers) {
		StopWatch sw = new StopWatch();
		sw.start();
		var result = studentService.getStudentDetailsByRollnumbersSingleThread(rollNumbers);
		sw.stop();
		result.setTimeTakenByService(String.valueOf(sw.getTotalTimeMillis()));
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@Override
	public ResponseEntity<StudentResponse> getAllStudent(Integer pageNumber, Integer pageSize) {
		StopWatch sw = new StopWatch();
		sw.start();
		var result = studentService.getAllStudentDetails(pageNumber, pageSize);
		sw.stop();
		result.setTimeTakenByService(String.valueOf(sw.getTotalTimeMillis()));
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

public 

}
