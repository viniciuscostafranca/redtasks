package com.franca.informatica.domain.user;

@SuppressWarnings("serial")
public class DuplicateAppUserException  extends Exception{

	public DuplicateAppUserException(String message) {
		super(message);
		
	}

}
