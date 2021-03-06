package com.franca.informatica.infrastructure.web;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.franca.informatica.domain.specialty.DuplicateSpecialtyException;
import com.franca.informatica.domain.user.DuplicateAppUserException;
import com.franca.informatica.domain.user.NotFoundAppUserException;

@RestControllerAdvice
public class WebResquestExceptionHandler {
	
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String handlerException (RepositoryConstraintViolationException rep) {
		return RestResponseError.fromValidationError(rep.getErrors());
	}
	

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestResponseError handlerException (DuplicateSpecialtyException rep) {
		return RestResponseError.fromMessage(rep.getMessage());
	}
	
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestResponseError handlerException (DuplicateAppUserException rep) {
		return RestResponseError.fromMessage(rep.getMessage());
	}
	

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestResponseError handlerException (NotFoundAppUserException rep) {
		return RestResponseError.fromMessage(rep.getMessage());
	}
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestResponseError handlerException (MethodArgumentNotValidException rep) {
		return RestResponseError.fromMessage(rep.getMessage());
	}
	
}
