package com.amit.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseTo {
	
	private String errorCode;
	private String errorMessage;
	private LocalDateTime errorTime;

}
