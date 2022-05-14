package com.veterinaryserver.test.helper;

import com.veterinaryserver.entity.user.User;
import com.veterinaryserver.entity.user.UserRole;
import com.veterinaryserver.model.request.RegistrationRequest;

public class UserHelper {

	public static final String NAME = "Taha";
	
	public static final String SURNAME = "Filiz";
	public static final String USERNAME = "tahaflz123";
	public static final String NATIVE_PASSWORD = "123123";
	public static final String PHONE_NUMBER = "0500000";
	
	
	
	public static User createUser() {
		return User.builder()
				.name(NAME)
				.surname(SURNAME)
				.username(USERNAME)
				.phoneNumber(NAME)
				.password(NATIVE_PASSWORD)
				.role(UserRole.USER)
				.build();
	}
	
	public static User createAdminUser() {
		return User.builder()
				.name(NAME)
				.surname(SURNAME)
				.username(USERNAME)
				.phoneNumber(NAME)
				.password(NATIVE_PASSWORD)
				.role(UserRole.ADMIN)
				.build();
	}

	public static RegistrationRequest createRegistrationRequestWithCustomUsername(String username) {
		return RegistrationRequest.builder()
				.name(NAME)
				.surname(SURNAME)
				.username(username)
				.phoneNumber(PHONE_NUMBER)
				.password(NATIVE_PASSWORD)
				.build();
	}
	
}
