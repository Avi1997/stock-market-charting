package com.cognizant.authenticationservice.service;

public interface UserConfirmationService {

	public String setTokenForConfirmation(String userId);

	public void confirmMailAddress(String token);
}
