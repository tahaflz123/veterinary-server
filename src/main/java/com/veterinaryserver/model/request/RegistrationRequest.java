package com.veterinaryserver.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

	private String name;
	
	private String surname;
	
	private String username;
	
	private String phoneNumber;
	
	private String password;
	
}
