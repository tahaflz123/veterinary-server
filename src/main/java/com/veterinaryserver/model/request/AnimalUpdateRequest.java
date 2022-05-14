package com.veterinaryserver.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalUpdateRequest {

	private String name;
	
	private String gender;
	
	private String type;
	
	private String about;
	
	private Short age;
	
	private String breed;
	
}
