package com.veterinaryserver.test.helper;

import com.veterinaryserver.entity.animal.Animal;
import com.veterinaryserver.entity.animal.AnimalType;
import com.veterinaryserver.entity.animal.Gender;
import com.veterinaryserver.entity.user.User;
import com.veterinaryserver.model.request.AnimalCreationRequest;
import com.veterinaryserver.model.request.AnimalUpdateRequest;

public class AnimalHelper {

	public static final String NAME = "Limon";
	public static final Short AGE = 1;
	public static final String BREED = "Parrot";
	public static final AnimalType TYPE = AnimalType.BIRD;
	public static final Gender GENDER = Gender.MALE;
	public static final String ABOUT = "About limon";
	public static final User USER = UserHelper.createUser();
	
	
	public static Animal createAnimal() {
		return Animal.builder()
				.name(NAME)
				.age(AGE)
				.breed(BREED)
				.type(TYPE)
				.gender(GENDER)
				.about(ABOUT)
				.user(USER)
				.build();
	}
	
	public static AnimalCreationRequest createAnimalCreationRequest() {
		return AnimalCreationRequest.builder()
				.name(NAME)
				.age(AGE)
				.breed(BREED)
				.type(TYPE)
				.gender(GENDER)
				.about(ABOUT)
				.build();
	}
	
	public static AnimalUpdateRequest createAnimalUpdateRequest() {
		return AnimalUpdateRequest.builder()
				.name("Rex")
				.age((short) 10)
				.breed("Golden")
				.type("DOG")
				.gender("FEMALE")
				.about("About golden dog Rex")
				.build();
				
	}

	public static Animal createAnimal(User loggedIn) {
		return Animal.builder()
				.name(NAME)
				.age(AGE)
				.breed(BREED)
				.type(TYPE)
				.gender(GENDER)
				.about(ABOUT)
				.user(loggedIn)
				.build();
	}
}
