package com.cognizant.authenticationservice.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.authenticationservice.model.User;
import com.cognizant.authenticationservice.repository.UserRepository;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/stock-market")
public class AuthenticationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
	@Autowired
	UserRepository userRepository;

	@GetMapping("/authenticate")
	public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
		LOGGER.info("Authenticate START");
		LOGGER.debug("authHeader : {}", authHeader);
		Map<String, String> map = new HashMap<String, String>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String role = authentication.getAuthorities().toArray()[0].toString();
		String username = authentication.getName();
		map.put("token", generateJwt(getUser(authHeader)));
		map.put("role", role);
		map.put("user", username);
		User user = userRepository.findByUsername(username);
		map.put("confirm", "" + user.isConfirm());
		LOGGER.debug("map: {}", map);
		LOGGER.info("Authenticate END");
		return map;
	}

	private String getUser(String authHeader) {
		LOGGER.info("START");
		String encodedCredentials = authHeader.split(" ")[1];
		String decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials));
		String user = decodedCredentials.split(":")[0];
		LOGGER.debug("user : {}", user);
		LOGGER.info("END");
		return user;
	}

	private String generateJwt(String user) {
		LOGGER.info("START");
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);
		builder.setIssuedAt(new Date());
		builder.setExpiration(new Date((new Date()).getTime() + 1200000));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");
		String token = builder.compact();
		LOGGER.debug("token : {}", token);
		LOGGER.info("END");
		return token;
	}
}