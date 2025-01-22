package com.amit.entity;

import jakarta.persistence.Tuple;
import lombok.Getter;

import static com.amit.util.constant.DbConstants.*;

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
		this.addressId = tuple.get(ADDRESS_ID, Integer.class);
		this.line1 = tuple.get(ADDRESS_LINE_1, String.class);
		this.line2 = tuple.get(ADDRESS_LINE_2, String.class);
		this.city = tuple.get(ADDRESS_CITY, String.class);
		this.state = tuple.get(ADDRESS_STATE, String.class);
		this.pincode = tuple.get(ADDRESS_PINCODE, String.class);
		this.country = tuple.get(ADDRESS_COUNTRY, String.class);
		this.studentId = tuple.get(STUDENT_ID, Integer.class);
	}
	
	

}
