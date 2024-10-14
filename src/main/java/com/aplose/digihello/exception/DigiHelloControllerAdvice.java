package com.aplose.digihello.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DigiHelloControllerAdvice {
	@ExceptionHandler({ DigiHelloException.class})
	protected ResponseEntity<String> traiterErreurs(DigiHelloException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
