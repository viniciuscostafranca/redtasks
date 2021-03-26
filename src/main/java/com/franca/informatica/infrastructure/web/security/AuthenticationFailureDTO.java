package com.franca.informatica.infrastructure.web.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationFailureDTO {

	private String message;


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static String buildToJSon(String message) throws JsonProcessingException {

		AuthenticationFailureDTO authenticationFailureDTO = new AuthenticationFailureDTO();
		authenticationFailureDTO.setMessage(message);
		
		return new ObjectMapper().writeValueAsString(authenticationFailureDTO);
	}

}
