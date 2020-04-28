package com.cognizant.authenticationservice.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.authenticationservice.model.User;
import com.cognizant.authenticationservice.model.UserConfirmation;
import com.cognizant.authenticationservice.repository.UserConfirmationRepository;
import com.cognizant.authenticationservice.repository.UserRepository;

@Service
public class UserConfirmationServiceImpl implements UserConfirmationService {

	@Autowired
	UserConfirmationRepository confirmationRepository;
	@Autowired
	UserRepository userRepository;
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	@Transactional
	public String setTokenForConfirmation(String userId) {
		String token = randomAlphaNumeric();
		UserConfirmation userConfirmation = new UserConfirmation(token, userId);
		confirmationRepository.save(userConfirmation);
		return token;
	}

	@Transactional
	public void confirmMailAddress(String token) {
		UserConfirmation userConfirmation = confirmationRepository.findByToken(token);
		if (userConfirmation != null) {
			confirmationRepository.delete(userConfirmation);
			User user = userRepository.findByUsername(userConfirmation.getUserId());
			user.setConfirm(true);
			userRepository.save(user);
		}
	}

	public static String randomAlphaNumeric() {

		int count = 10;
		StringBuilder builder = new StringBuilder();

		while (count-- != 0) {

			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());

			builder.append(ALPHA_NUMERIC_STRING.charAt(character));

		}

		return builder.toString();

	}
}
