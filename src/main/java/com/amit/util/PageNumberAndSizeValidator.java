package com.amit.util;

import com.amit.exception.PageInvalidException;

public class PageNumberAndSizeValidator {
	
	public static void validatePage(Integer pageNumber, Integer pageSize) {
		if(pageNumber <= 0) {
			throw new PageInvalidException("Page number can only be possitive number");
		}
		else if(pageSize <= 0) {
			throw new PageInvalidException("Page size can only be possitive number");
		}
	}

}
