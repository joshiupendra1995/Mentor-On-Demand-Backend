package com.lti.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Invalid User")
public class InvalidUserException extends RuntimeException {

	private static final long serialVersionUID = 1584294867415087270L;

	public InvalidUserException(String message) {
		super(message);
	}
}
