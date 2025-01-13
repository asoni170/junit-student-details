package com.amit.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
	
	private Integer totalRecords;
	private List<StudentDetails> students;
	private String timeTakenByService;

}
