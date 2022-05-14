package com.veterinaryserver.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.veterinaryserver.model.request.RegistrationRequest;
import com.veterinaryserver.test.helper.UserHelper;




@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerIT {
/*
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	void given_registration_request_when_register_then_it_should_return_200_and_user() {
    	try {
			mockMvc.perform(get("/api/user/register"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/html"))
			.andExpect(view().name("user/register"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test 
	void test() {
		RegistrationRequest registrationRequest = UserHelper.createRegistrationRequestWithCustomUsername("testuser");
		
		JsonNode jsonNode = objectMapper.valueToTree(registrationRequest);
		MvcResult mvcResult = null;
		try {
			mvcResult = mockMvc.perform(post("/api/user/register")
					.contentType("application/json")
					.content(jsonNode.asText())).andExpect(status().isOk()).andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println(mvcResult.toString());
	}
	*/
}
