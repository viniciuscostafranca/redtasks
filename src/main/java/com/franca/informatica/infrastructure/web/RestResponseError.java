package com.franca.informatica.infrastructure.web;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

public class RestResponseError {
	
	private String error;
	public RestResponseError() {
		
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	public static String fromValidationError(Errors errors) {
		
	
		StringBuffer str = new StringBuffer();
		
		errors.getAllErrors().forEach(err -> {
			
			str.append(err.getDefaultMessage());
			str.append(". ");
		});
		
	
		return str.toString();
		
	}
	
	
	public static RestResponseError fromMessage(String message) {
		RestResponseError  errorMessage = new RestResponseError();
		errorMessage.setError(message);
		return errorMessage;
		
	}
	
	

}
