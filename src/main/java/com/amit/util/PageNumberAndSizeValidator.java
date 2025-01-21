package com.amit.util;

import com.amit.exception.PageInvalidException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.amit.util.constant.CommonConstants.INVALID_PAGE_NUMBER;
import static com.amit.util.constant.CommonConstants.INVALID_PAGE_SIZE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageNumberAndSizeValidator {
	
	public static void validatePage(Integer pageNumber, Integer pageSize) {
		if(pageNumber == null || pageNumber <= 0) {
			throw new PageInvalidException(INVALID_PAGE_NUMBER);
		}
		else if(pageSize == null || pageSize <= 0) {
			throw new PageInvalidException(INVALID_PAGE_SIZE);
		}
	}

}
