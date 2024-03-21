package com.PRJ321x_namtqFX20225.asm3.rest;

public class ErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorException(String message) {
		super(message);
	}

	public ErrorException(Throwable cause) {
		super(cause);
	}

}
