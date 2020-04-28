package com.cognizant.authenticationservice;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cognizant.authenticationservice.dto.UserDto;
import com.cognizant.authenticationservice.exception.UserAlreadyExistsException;
import com.cognizant.authenticationservice.model.Role;
import com.cognizant.authenticationservice.model.User;
import com.cognizant.authenticationservice.repository.RoleRepository;
import com.cognizant.authenticationservice.repository.UserRepository;
import com.cognizant.authenticationservice.security.AppUserDetailsService;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AuthenticationServicesTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServicesTest.class);
 	
	UserRepository repository = Mockito.mock(UserRepository.class);
	
	RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
 	
	AppUserDetailsService service = new AppUserDetailsService(repository,roleRepository); 
 
 	@Test
 	public void mockTestLoadUserByUsername() {
 		LOGGER.info("Start");
 		when(repository.findByUsername("admin")).thenReturn(createUser());
 		UserDetails user = service.loadUserByUsername("admin");
 		String expected = "$2a$10$IoqonpxYeSWir9ir16Pb6.8sCa444mtsH6R6oH.ioWnHkpODsBsPS";
 		assertEquals(expected, user.getPassword());
 		LOGGER.info("End");
 	}
 	private User createUser() {
 		Set<Role> role = new HashSet<Role>();
		role.add(new Role(1, "ADMIN"));
 		User user = new User(1,"admin","$2a$10$IoqonpxYeSWir9ir16Pb6.8sCa444mtsH6R6oH.ioWnHkpODsBsPS","admin@gmail.com","1234356788",true,role);
 		return user;
 	}
 	@Test(expected = UsernameNotFoundException.class)
 	public void testLoadByUserNameNotFoundException() throws UsernameNotFoundException {
 		when(repository.findByUsername("rohith")).thenReturn(null);
 		service.loadUserByUsername("rohith");
 	} 
 	@Test(expected = UserAlreadyExistsException.class)
 	public void testForUserExist() throws UserAlreadyExistsException {
 		LOGGER.info("Start");
 		Set<Role> role = new HashSet<Role>();
		role.add(new Role(1, "ADMIN"));
 		User user = new User(1,"admin","$2a$10$IoqonpxYeSWir9ir16Pb6.8sCa444mtsH6R6oH.ioWnHkpODsBsPS","admin@gmail.com","1234356788",true,role);
 		when(repository.findByUsername("admin")).thenReturn(user);
 		UserDto  userDto = new UserDto("admin","$2a$10$IoqonpxYeSWir9ir16Pb6.8sCa444mtsH6R6oH.ioWnHkpODsBsPS","admin@gmail.com","1234356788");
 		
 		service.signup(userDto);
 		LOGGER.info("End");
 	}
 	@Test
 	public void newSignUp() throws UserAlreadyExistsException {
 		LOGGER.info("Start");
 		Set<Role> role = new HashSet<Role>();
 		
 		Optional<Role> optional = Optional.of(new Role(1, "ADMIN"));
		role.add(new Role(1, "ADMIN"));
 		when(repository.findByUsername("rohith")).thenReturn(null);
 		when(roleRepository.findById(1)).thenReturn(optional);
 			UserDto  userDto = new UserDto("admin","$2a$10$IoqonpxYeSWir9ir16Pb6.8sCa444mtsH6R6oH.ioWnHkpODsBsPS","admin@gmail.com","1234356788",1);
 		service.signup(userDto); 		
 		LOGGER.info("End");
 
 	}
}
