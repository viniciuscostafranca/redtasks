package com.franca.informatica.infrastructure.web;

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
	
	public static RestResponseError fromValidationError(Errors errors) {
		
		RestResponseError responseError = new RestResponseError();
		
		StringBuffer str = new StringBuffer();
		
		errors.getAllErrors().forEach(err -> {
			
			str.append(err.getDefaultMessage());
			str.append(". ");
		});
		responseError.setError(str.toString());
		
		return responseError;
		
		
	}
	
	
	public static RestResponseError fromMessage(String message) {
		RestResponseError  errorMessage = new RestResponseError();
		errorMessage.setError(message);
		return errorMessage;
		
	}
	
	

}
