package com.lti.app.exception;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javassist.bytecode.DuplicateMemberException;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(InvalidUserException.class)
	public final ResponseEntity<Object> handleAllInvalidUserExceptions(InvalidUserException ex, WebRequest request) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), HttpStatus.UNAUTHORIZED.value(), timestamp);
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public final ResponseEntity<Object> handleAllEmptyResultDataExceptions(EmptyResultDataAccessException ex,
			WebRequest request) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), HttpStatus.SERVICE_UNAVAILABLE.value(),
				timestamp);
		return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@ExceptionHandler(DuplicateMemberException.class)
	public final ResponseEntity<Object> handleAllDuplicateMemberExceptions(DuplicateMemberException ex, WebRequest request) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), HttpStatus.SERVICE_UNAVAILABLE.value(), timestamp);
		return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
	}
}
