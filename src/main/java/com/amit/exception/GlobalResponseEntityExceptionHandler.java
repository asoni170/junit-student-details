package com.amit.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.amit.response.ErrorResponseTo;

@ControllerAdvice
public class GlobalResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseTo> handleResourceNotFoundException(
			ResourceNotFoundException exception){
		
		var error = new ErrorResponseTo(HttpStatus.NOT_FOUND.toString(),
				exception.getMessage(), LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		
		var error = new ErrorResponseTo(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
				ex.getMessage(), LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

	@ExceptionHandler(ApiException.class)
	public final ResponseEntity<Object> handleAllException(ApiException ex, WebRequest request) {

		var error = new ErrorResponseTo(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
				ex.getMessage(), LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

}
