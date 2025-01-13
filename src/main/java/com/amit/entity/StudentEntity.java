package com.amit.entity;

import com.amit.util.constant.DbConstants;

import jakarta.persistence.Tuple;
import lombok.Getter;

@Getter
public class StudentEntity {

	private Integer studentId;

	private String studentName;

	private String rollNumber;

	public StudentEntity(Tuple tuple) {
		this.studentId = tuple.get(DbConstants.STUDENT_ID, Integer.class);
		this.studentName = tuple.get(DbConstants.STUDENT_NAME, String.class);
		this.rollNumber = tuple.get(DbConstants.STUDENT_ROLL_NUMBER, String.class);
	}
    
    
}
