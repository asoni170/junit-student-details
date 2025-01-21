package com.amit.util.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlQueryConstants {
	
	public static final String FIELD_ROLL_NUMBER_LIST = "studentRollnumbers";
	public static final String FIELD_STUDENT_ID_LIST = "studentIds";
	
	public static final String PAGE_NUMBER = "pageNumber";
	public static final String PAGE_SIZE = "pageSize";
	
	public static final String SELECT_STUDENT_BY_ROLL_NUMBERS_IN = "SELECT_STUDENT_BY_ROLL_NUMBERS_IN";
	public static final String SELECT_ADDRESS_BY_STUDENT_ID = "SELECT_ADDRESS_BY_STUDENT_ID";
	public static final String SELECT_COMMUNICATION_BY_STUDENT_ID = "SELECT_COMMUNICATION_BY_STUDENT_ID";
	public static final String SELECT_STUDENT_TOTAL_COUNT = "SELECT_STUDENT_TOTAL_COUNT";
	public static final String SELECT_ALL_STUDENT_PAGINATED = "SELECT_ALL_STUDENT_PAGINATED";

	public static final String MOCK_QUERY_KEY = "MOCK_QUERY_KEY";
	public static final String MOCK_QUERY_STRING = "MOCK_QUERY_STRING";

}
