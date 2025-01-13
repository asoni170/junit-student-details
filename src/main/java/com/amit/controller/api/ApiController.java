package com.amit.controller.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amit.entity.StudentEntity;
import com.amit.response.StudentResponse;

@RestController
@RequestMapping(path = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ApiController {

	@GetMapping("/rollnumbers")
	public ResponseEntity<List<StudentEntity>> getStudentListByRollnumber(
			@RequestParam List<String> rollNumbers);
	
	@GetMapping("/details")
	public ResponseEntity<StudentResponse> getStudentDetailsByRollnumber(
			@RequestParam List<String> rollNumbers);
	
	@GetMapping("/details/singlethread")
	public ResponseEntity<StudentResponse> getStudentDetailsByRollnumberSinglethread(
			@RequestParam List<String> rollNumbers);
	
	@GetMapping("/all")
	public ResponseEntity<StudentResponse> getAllStudent(
			@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize);

}
