package com.veterinaryserver.model.request;


import com.veterinaryserver.entity.animal.AnimalType;
import com.veterinaryserver.entity.animal.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalCreationRequest {

	private String name;
	
	private AnimalType type;
	
	private String breed;
	
	private Short age;
	
	private Gender gender;
	
	private String about;
	
}
