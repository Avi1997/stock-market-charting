package com.cognizant.authenticationservice.model;



import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "user")
public class User {
	private static final Logger LOGGER = LoggerFactory.getLogger(Role.class);
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "us_id")
	private int id;

	@NotNull
	@Size(min = 2, message = "Atleast 2 characters are required")
	@Column(name = "us_username")
	String username;
	
	@NotNull
	@Column(name = "us_password")
	String password;
		
	@NotNull
	@Column(name="us_email")
	String email;
	
	@NotNull
	@Column(name="us_contact_number")
	String contactNumber;
	
	@NotNull
	@Column(name="us_confirm")
	boolean confirm;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_has_role", joinColumns = @JoinColumn(name = "ur_us_id"), 
		inverseJoinColumns = @JoinColumn(name = "ur_ro_id"))
	private Set<Role> roleList;	

	public User() {
		super();
	}
	
	public User(int id, @NotNull @Size(min = 2, message = "Atleast 2 characters are required") String username,
			@NotNull String password,  @NotNull String email, @NotNull String contactNumber,
			Set<Role> roleList) {
		super();
		LOGGER.info("user Model Start->End");
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
		this.roleList = roleList;
	}

	public User(@NotNull @Size(min = 2, message = "Atleast 2 characters are required") String username,
			@NotNull String password, @NotNull String email, @NotNull String contactNumber,
			Set<Role> roleList) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
		this.roleList = roleList;
	}
	
	
	public User(int id, @NotNull @Size(min = 2, message = "Atleast 2 characters are required") String username,
			@NotNull String password, @NotNull String email, @NotNull String contactNumber, @NotNull boolean confirm,
			Set<Role> roleList) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
		this.confirm = confirm;
		this.roleList = roleList;
	}

	public int getId() {
		return id; 
	}

	public void setId(int id) {
		this.id = id;
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

	public Set<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<Role> roleList) {
		this.roleList = roleList;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	
}