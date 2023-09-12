package com.userlocation.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserLocationNotFoundException.class)
	public ResponseEntity<ErrorDetails>userLocationNotFound(UsernameNotFoundException ex, WebRequest webRequest){
		ErrorDetails error=new ErrorDetails(new Date(), ex.getMessage(),webRequest.getDescription(false) );
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}

}
