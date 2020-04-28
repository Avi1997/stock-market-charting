package com.cognizant.authenticationservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User already exists")
public class UserAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAlreadyExistsException.class);

	public UserAlreadyExistsException() {
		super();
		LOGGER.info("START");
	}

	@Override
	public String toString() {
		return "User already exists !!";
	}
}