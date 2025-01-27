package com.amit.util.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonConstants {
	
	public static final String RESOURCE_STUDENT = "Student";
	public static final String RESOURCE_ADDRESS = "Address";
	public static final String RESOURCE_COMMUNICATION = "Communication";
	
	public static final String PARAMETER_ROLLNUMBER = "Rollnumber";

	public static final String TECHNICAL_ERROR_MESSAGE = "Technical Error occured, please contact system administrator";
	public static final String INVALID_PAGE_NUMBER = "Page number can only be possitive number";
	public static final String INVALID_PAGE_SIZE = "Page size can only be possitive number";

	public static final String MOCK_STUDENT_NAME = "Demo Name";
	public static final String MOCK_STUDENT_ROLLNUMBER = "ROLL001";
	public static final Integer MOCK_STUDENT_ID = 10001;

	public static final Integer MOCK_ADDRESS_ID = 20001;
	public static final String MOCK_ADDRESS_LINE_1 = "Demo Address Line 1";
	public static final String MOCK_ADDRESS_LINE_2 = "Demo Address Line 2";
	public static final String MOCK_ADDRESS_CITY = "Demo City";
	public static final String MOCK_ADDRESS_STATE = "Demo State";
	public static final String MOCK_ADDRESS_PINCODE = "Demo Pincode";
	public static final String MOCK_ADDRESS_COUNTRY = "Demo Country";

	public static final Integer MOKC_COMMUNICATION_ID = 3001;
	public static final String MOCK_COMMUNICATION_CHANNEL_TYPE = "Phone";
	public static final String MOCK_COMMUNICATION_CHANNEL_VALUE = "9876543210";

}
