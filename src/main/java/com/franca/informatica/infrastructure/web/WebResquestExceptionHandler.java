package com.franca.informatica.infrastructure.web;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.franca.informatica.domain.task.DuplicateTaskException;

@RestControllerAdvice
public class WebResquestExceptionHandler {
	
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestResponseError handlerException (RepositoryConstraintViolationException rep) {
		return RestResponseError.fromValidationError(rep.getErrors());
	}
	

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestResponseError handlerException (DuplicateTaskException rep) {
		return RestResponseError.fromMessage(rep.getMessage());
	}
	
	
}
