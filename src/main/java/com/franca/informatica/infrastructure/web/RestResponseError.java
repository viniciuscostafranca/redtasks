package com.franca.informatica.infrastructure.web;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

public class RestResponseError {
	
	private CustomErrorResponse error;
	public RestResponseError() {
		
	}
	public CustomErrorResponse getError() {
		return error;
	}
	public void setError(CustomErrorResponse error) {
		this.error = error;
	}
	
	public static ResponseEntity<CustomErrorResponse> fromValidationError(Errors errors) {
		
	
		StringBuffer str = new StringBuffer();
		
		errors.getAllErrors().forEach(err -> {
			
			str.append(err.getDefaultMessage());
			str.append(". ");
		});
		
	
		CustomErrorResponse customErros = new CustomErrorResponse();
		customErros.setTimestamp(LocalDateTime.now());
		customErros.setError(str.toString());
		customErros.setStatus(HttpStatus.NOT_FOUND.value());
      
	
        return new ResponseEntity<CustomErrorResponse>(customErros, HttpStatus.NOT_FOUND);
		
		
	}
	
	
	public static RestResponseError fromMessage(String message) {
		RestResponseError  errorMessage = new RestResponseError();
		//errorMessage.setError(message);
		return errorMessage;
		
	}
	
	

}
