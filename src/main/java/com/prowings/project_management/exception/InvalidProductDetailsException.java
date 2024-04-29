package com.prowings.project_management.exception;

public class InvalidProductDetailsException extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2053955694167621605L;

	public InvalidProductDetailsException() {
		super();
	}

	public InvalidProductDetailsException(String message) {
		super(message);
	}

}
