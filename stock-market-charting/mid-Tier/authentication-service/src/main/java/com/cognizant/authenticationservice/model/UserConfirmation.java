package com.cognizant.authenticationservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="confirm_email")
public class UserConfirmation {

	@Id
	@Column(name = "ce_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "ce_token")
	private String token;
	@Column(name = "ce_us_name")
	private String userId;

	public UserConfirmation(String token, String userId) {
		super();
		this.token = token;
		this.userId = userId;
	}

	public UserConfirmation() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserConfirmation [id=" + id + ", token=" + token + ", userId=" + userId + "]";
	}

}
