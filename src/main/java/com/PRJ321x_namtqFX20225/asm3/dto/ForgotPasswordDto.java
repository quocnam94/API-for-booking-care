package com.PRJ321x_namtqFX20225.asm3.dto;

public class ForgotPasswordDto {
	private String email;
	private String password;
	private String matchingPassword;

	public ForgotPasswordDto() {
	}

	public ForgotPasswordDto(String email, String password, String matchingPassword) {
		this.email = email;
		this.password = password;
		this.matchingPassword = matchingPassword;
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

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

}
