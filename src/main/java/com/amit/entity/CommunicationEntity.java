package com.amit.entity;

import com.amit.util.constant.DbConstants;

import jakarta.persistence.Tuple;
import lombok.Getter;

@Getter
public class CommunicationEntity {

	private Integer commId;

	private String commChannel;

	private String commValue;
	
	private Integer studentId;

	public CommunicationEntity(Tuple tuple) {
		this.commId = tuple.get(DbConstants.COMMUNICATION_ID, Integer.class);
		this.commChannel = tuple.get(DbConstants.COMMUNICATION_CHANNEL_TYPE, String.class);
		this.commValue = tuple.get(DbConstants.COMMUNICATION_CHANNEL_VALUE, String.class);
		this.studentId = tuple.get(DbConstants.STUDENT_ID, Integer.class);
	}
}
