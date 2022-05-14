package com.veterinaryserver.exception;

public class VeterinaryException extends RuntimeException{

	private String message;
	
	private ErrorCode errorCode;
	
	
	public VeterinaryException(ErrorCode errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public String getErrorCode() {
		return errorCode.name();
	}
	
	public int getStatusCode(){
		return errorCode.getStatusCode();
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}
