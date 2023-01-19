package com.accenture.hackathon.error;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	 
	@ExceptionHandler(GenericDeviationException.class)
	public ResponseEntity<ErrorMessage> genericDeviationException(GenericDeviationException exception,
																WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.I_AM_A_TEAPOT, exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
				.body(message);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorMessage> noSuchElementException(NoSuchElementException exception,
																WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(message);
	}
}