package com.example.guestprofileservice.exception;

public class GuestNotFoundException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public GuestNotFoundException(String message) {
		super(message);
	}

}
