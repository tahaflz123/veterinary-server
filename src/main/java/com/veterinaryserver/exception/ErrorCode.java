package com.veterinaryserver.exception;

public enum ErrorCode {

	UNAUTHORIZED(401),
	NO_RESOURCE (410),
	CREDENTIALS_NOT_MATCHING (401),
	USERNAME_ALREADY_IN_USAGE (401),
	GENDER_IS_VALID (401),
	FORBIDDEN (403),
	BAD_REQUEST (400);
	
	private int statusCode;

	private ErrorCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
