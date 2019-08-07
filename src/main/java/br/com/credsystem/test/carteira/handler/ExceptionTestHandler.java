package br.com.credsystem.test.carteira.handler;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.credsystem.test.carteira.response.DefaultResponse;

@ControllerAdvice
public class ExceptionTestHandler {

	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public DefaultResponse dataFoundExceptionHandlerError(EmptyResultDataAccessException ex) {
		DefaultResponse response = new DefaultResponse();
		response.setMessage(ex.getMessage());
		return response;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public DefaultResponse ExceptionHandlerError(Exception ex) {
		DefaultResponse response = new DefaultResponse();
		response.setMessage(ex.getMessage());
		return response;
	}
	
}
