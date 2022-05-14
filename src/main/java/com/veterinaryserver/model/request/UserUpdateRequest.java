package com.veterinaryserver.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequest {

	private String name;
	
	private String surname;
	
	private String username;
	
	private String phoneNumber;
}
