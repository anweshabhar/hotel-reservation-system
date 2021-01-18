package com.example.apigateway.filter;

public class GatewayAuthenticationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GatewayAuthenticationException(String message) {
		super(message);
	}

}
