package com.cognizant.authenticationservice.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.authenticationservice.dto.UserDto;
import com.cognizant.authenticationservice.exception.UserAlreadyExistsException;
import com.cognizant.authenticationservice.model.User;
import com.cognizant.authenticationservice.repository.RoleRepository;
import com.cognizant.authenticationservice.repository.UserRepository;
import com.cognizant.authenticationservice.security.AppUserDetailsService;
import com.cognizant.authenticationservice.service.EmailService;
import com.cognizant.authenticationservice.service.UserConfirmationService;

@RestController
@RequestMapping("/stock-market")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	AppUserDetailsService appUserDetailsService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserConfirmationService userConfirmationService;
	
	@Autowired
	EmailService emailService;

	@GetMapping("/user/{username}")
	public User getAllUsers(@PathVariable String username) {
		return userRepository.findByUsername(username);
	}

	@PostMapping("/user")
	public boolean updateDetails(@RequestBody @Valid UserDto userDto) {
		User user = userRepository.findByUsername(userDto.getUsername());
		if (!user.getPassword().equals(userDto.getPassword()))
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setContactNumber(userDto.getContactNumber());
		userRepository.save(user);
		return true;
	}

	@PostMapping("/sign-up")
	public boolean signup(@RequestBody @Valid UserDto userDto) throws UserAlreadyExistsException {
		LOGGER.info("SIGNUP controller Start");
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		boolean status = appUserDetailsService.signup(userDto);
		String token = userConfirmationService.setTokenForConfirmation(userDto.getUsername());
		emailService.send("ctstestmail10@gmail.com", userDto.getEmail(),
				"confirm your email for Stock Exchange by clicking here",
				"http://localhost:8083/authentication-service/stock-market/confirm/" + token);
		LOGGER.info("SIGNUP controller End");
		return status;
	}

	@GetMapping("/confirm/{token}")
	public void confirmMail(@PathVariable String token) {
		userConfirmationService.confirmMailAddress(token);
	}
}