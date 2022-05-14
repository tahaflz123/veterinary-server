package com.veterinaryserver.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAnimalAboutRequest {
	
	private String about;

}
