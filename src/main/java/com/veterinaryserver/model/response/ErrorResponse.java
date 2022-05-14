package com.veterinaryserver.model.response;

import java.time.ZonedDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

	private ZonedDateTime dateTime;
	
	private String message;
	
	private String errorCode;
	
	private int statusCode;

}
