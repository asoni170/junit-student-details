package com.amit.entity;

import com.amit.util.constant.DbConstants;

import jakarta.persistence.Tuple;
import lombok.Getter;

@Getter
public class AddressEntity {

	private Integer addressId;

	private String line1;

	private String line2;

	private String city;

	private String state;

	private String pincode;

	private String country;
	
	private Integer studentId;

	public AddressEntity(Tuple tuple) {
		this.addressId = tuple.get(DbConstants.ADDRESS_ID, Integer.class);
		this.line1 = tuple.get(DbConstants.ADDRESS_LINE_1, String.class);
		this.line2 = tuple.get(DbConstants.ADDRESS_LINE_2, String.class);
		this.city = tuple.get(DbConstants.ADDRESS_CITY, String.class);
		this.state = tuple.get(DbConstants.ADDRESS_STATE, String.class);
		this.pincode = tuple.get(DbConstants.ADDRESS_PINCODE, String.class);
		this.country = tuple.get(DbConstants.ADDRESS_COUNTRY, String.class);
		this.studentId = tuple.get(DbConstants.STUDENT_ID, Integer.class);
	}
	
	

}
