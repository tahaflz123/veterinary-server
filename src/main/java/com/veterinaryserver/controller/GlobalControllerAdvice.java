package com.veterinaryserver.controller;

import java.time.ZonedDateTime;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.veterinaryserver.exception.VeterinaryException;
import com.veterinaryserver.model.response.ErrorResponse;

@ControllerAdvice
public class GlobalControllerAdvice {

	
	@ExceptionHandler(VeterinaryException.class)
	public ErrorResponse exception(VeterinaryException exception) {
		return ErrorResponse.builder()
				.dateTime(ZonedDateTime.now())
				.message(exception.getMessage())
				.statusCode(exception.getStatusCode())
				.errorCode(exception.getErrorCode())
				.build();		
	}

}
