package com.userlocation.exception;

public class UserLocationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserLocationNotFoundException(String message) {
		super(message);
	}

}
