package com.exceptions;

public class UserDoesNotExist extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2205221927594768055L;
	
	public UserDoesNotExist() {
		super("Username does not exist");
	}

}
