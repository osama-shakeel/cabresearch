package com.au.research.cab.exception;

/**
 * VO class that represents a REST API error.
 *
 */
public class ApiError {
	private int status;
    private String error;
    private String message;
    
	public ApiError(String message, int status, String error) {
		this.error = error;
		this.message = message;
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getError() {
		return error;
	}
}
