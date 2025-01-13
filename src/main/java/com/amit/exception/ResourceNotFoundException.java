package com.amit.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 682321076767700009L;

	public ResourceNotFoundException(
			String resourceName, String parameterName, String parameterValue) {
		
		super(String.format("%s details not found with provided %s as %s", 
				resourceName, parameterName, parameterValue));
	}

}
