package com.cognizant.authenticationservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {
	@NotNull
	@Size(min = 4, message = "Atleast 4 characters are required")
	String username;
	@NotNull
	String password;
	@NotNull
	String email;
	@NotNull
	String contactNumber;
	private int role;

	public UserDto() {
		super();
	}

	public UserDto(String username, String password, String email, String contactNumber) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
	}

	public UserDto(@NotNull @Size(min = 4, message = "Atleast 4 characters are required") String username,
			@NotNull String password, @NotNull String email, @NotNull String contactNumber, int role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
