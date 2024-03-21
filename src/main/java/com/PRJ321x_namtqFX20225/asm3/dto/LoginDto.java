package com.PRJ321x_namtqFX20225.asm3.dto;

public class LoginDto {
	private String email;
	private String password;

	public LoginDto() {
	}

	public LoginDto(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
