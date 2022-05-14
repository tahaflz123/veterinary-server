package com.veterinaryserver.model.response;

import java.util.List;

import com.veterinaryserver.entity.animal.Animal;
import com.veterinaryserver.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAndAnimalsResponse {

	private User user;
	
	private List<Animal> animals;
}
