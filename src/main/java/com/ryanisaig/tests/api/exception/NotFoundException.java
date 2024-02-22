package com.ryanisaig.tests.api.exception;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String message){
		new RuntimeException(message);	
	}
}
