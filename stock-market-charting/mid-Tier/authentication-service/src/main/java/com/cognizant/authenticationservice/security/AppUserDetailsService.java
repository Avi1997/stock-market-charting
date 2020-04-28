package com.cognizant.authenticationservice.security;

import java.util.LinkedHashSet;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.authenticationservice.dto.UserDto;
import com.cognizant.authenticationservice.exception.UserAlreadyExistsException;
import com.cognizant.authenticationservice.model.Role;
import com.cognizant.authenticationservice.model.User;
import com.cognizant.authenticationservice.repository.RoleRepository;
import com.cognizant.authenticationservice.repository.UserRepository;

@Service()
public class AppUserDetailsService implements UserDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppUserDetailsService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RoleRepository roleRepository;
	
	

	public AppUserDetailsService() {
		super();
	}
	public AppUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	public AppUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
		LOGGER.info("Start");
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		LOGGER.info("End");
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.info("loadUserByUsername Start");		
		User user = userRepository.findByUsername(username);
		LOGGER.debug("{loaded User}", user);
		AppUser appUser = null;
		if (user == null) {
			LOGGER.debug("Throwing the exception");
			throw new UsernameNotFoundException("User doesnot exist");
		} else {
			appUser = new AppUser(user);
		}
		LOGGER.info("loadUserByUsername End");
		return appUser;
	}

	@Transactional
	public boolean signup(UserDto userDto) throws UserAlreadyExistsException {
		LOGGER.info("signup Start");
		User existingUser = userRepository.findByUsername(userDto.getUsername());
		if (existingUser != null) {
			throw new UserAlreadyExistsException();
		}else {
			existingUser = new User(userDto.getUsername(),userDto.getPassword(),userDto.getEmail(), userDto.getContactNumber(),new LinkedHashSet<Role>());
			Role role;

			
			role = roleRepository.findById(userDto.getRole()).get();
			existingUser.getRoleList().add(role);
			existingUser.setConfirm(false);
		}
		userRepository.save(existingUser);
		LOGGER.info("signup End");
		return true;
	}

}