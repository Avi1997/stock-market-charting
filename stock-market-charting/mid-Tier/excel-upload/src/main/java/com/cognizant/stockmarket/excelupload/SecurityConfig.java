package com.cognizant.stockmarket.excelupload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cognizant.stockmarket.excelupload.security.AppUserDetailsService;
import com.cognizant.stockmarket.excelupload.security.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	AppUserDetailsService appUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		LOGGER.info("configure constructor Start");
		auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder());
		LOGGER.info("configure constructor end");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		LOGGER.info("{password encoder start -> END}");
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		LOGGER.info("Start");
		httpSecurity.cors();
		httpSecurity.csrf().disable().httpBasic().and().authorizeRequests()
				.antMatchers("/stock-market/upload-stock-data").permitAll().antMatchers("/stock-market/summary")
				.permitAll().anyRequest().authenticated().and()
				.addFilter(new JwtAuthorizationFilter(authenticationManager()));
		LOGGER.info("End");
	}

}