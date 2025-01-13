package com.amit.exception;

public class PageInvalidException extends RuntimeException {
	
	private static final long serialVersionUID = 436245634573451L;

	public PageInvalidException(String message) {
		super(message);
	}

}
