package com.franca.informatica.infrastructure.web;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.franca.informatica.domain.task.DuplicateTaskException;
import com.franca.informatica.domain.user.DuplicateAppUserException;
import com.franca.informatica.domain.user.NotFoundAppUserException;

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
