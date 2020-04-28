package com.cognizant.authenticationservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognizant.authenticationservice.dto.UserDto;
import com.cognizant.authenticationservice.security.AppUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AuthenticationMockMVCTest {

	@Autowired
	AppUserDetailsService appUserDetailsService;
	
	@Autowired
	private MockMvc mockMvc;

	
	@Test
	public void addUser() throws Exception {			
		mockMvc.perform(MockMvcRequestBuilders.post("/stock-market/sign-up")
				.content(asJsonString(new UserDto("uhdfrf","passwoe","adsmin@gmail.com","1234567890",1)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());				
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}