package com.PRJ321x_namtqFX20225.asm3.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ExceptionHandler {

//	@org.springframework.web.bind.annotation.ExceptionHandler
//	public ResponseEntity<ErrorResponse> handlerException(Exception ex) {
//		ErrorResponse error = new ErrorResponse();
//		error.setStatus(HttpStatus.BAD_REQUEST.value());
//		error.setMessage("Invalid amount");
//		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//	}
}
