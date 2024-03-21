package com.PRJ321x_namtqFX20225.asm3.rest;

public class ErrorResponse {
	private int status;
	private String message;

	public ErrorResponse() {
	}

	public ErrorResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
