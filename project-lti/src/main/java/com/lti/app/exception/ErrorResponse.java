package com.lti.app.exception;

import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
	private String errorMessage;
	private int errorCode;
	private Timestamp timestamp;

	public ErrorResponse(String errorMessage, int errorCode, Timestamp timestamp) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.timestamp = timestamp;
	}

}
