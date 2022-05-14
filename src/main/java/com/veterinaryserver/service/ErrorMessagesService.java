package com.veterinaryserver.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.veterinaryserver.exception.ErrorCode;

@Service
public class ErrorMessagesService {

	public Map<ErrorCode, String> messages = new HashMap<>();
	
	@PostConstruct
	public void postConstruct() {
		messages.put(ErrorCode.CREDENTIALS_NOT_MATCHING, "Given credentials not matching");
		messages.put(ErrorCode.NO_RESOURCE, "This resource is removed or no longer available");
		messages.put(ErrorCode.UNAUTHORIZED, "Unauthorized");
		messages.put(ErrorCode.USERNAME_ALREADY_IN_USAGE, "This username already in usage");
		messages.put(ErrorCode.GENDER_IS_VALID, "this gender is valid");
		messages.put(ErrorCode.FORBIDDEN, "You can't access here");
		messages.put(ErrorCode.BAD_REQUEST, "Please check the given parameters");
	}
	
	public String getMessage(ErrorCode code) {
		return messages.get(code);
	}
	
	
}
