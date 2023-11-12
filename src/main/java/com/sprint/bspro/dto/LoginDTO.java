package com.sprint.bspro.dto;

public class LoginDTO {
	private String username;
	private String password;
	private String role;
	private String email;
	private boolean isUsername;
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean getIsUsername() {
		return isUsername;
	}
	public void setIsUsername(boolean isUsername) {
		this.isUsername = isUsername;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LoginDTO(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role=role;
	}
	public LoginDTO(String username, String password, String role, boolean isUsername) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.isUsername = isUsername;
	}
	public LoginDTO() {
		super();
	}
	
}
