package com.cognizant.stockmarket.excelupload.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.stockmarket.excelupload.model.User;


public class AppUser implements UserDetails {
	private final Logger LOGGER = LoggerFactory.getLogger(AppUser.class);

	private static final long serialVersionUID = 1166800317717393789L;
	private User user;
	private Collection<? extends GrantedAuthority> authorities;

	public AppUser(User user) {
		LOGGER.info("Start");
		this.user = user;
		this.authorities = user.getRoleList().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		LOGGER.info("End");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		LOGGER.info("Start");
		LOGGER.info("End");
		return authorities;
	}

	@Override
	public String getPassword() {
		LOGGER.info("Start");
		LOGGER.info("End");
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		LOGGER.info("Start");
		LOGGER.info("End");
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		LOGGER.info("Start");
		LOGGER.info("End");
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		LOGGER.info("Start");
		LOGGER.info("End");
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		LOGGER.info("Start");
		LOGGER.info("End");
		return true;
	}

	@Override
	public boolean isEnabled() {
		LOGGER.info("Start");
		LOGGER.info("End");
		return true;
	}

}