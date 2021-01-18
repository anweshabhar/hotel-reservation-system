package com.example.apigateway.request;

public class AuthenticateResponse {

	private final String jwt;

	public AuthenticateResponse(String jwt) {
		this.jwt = jwt;
	}

	/**
	 * @return the jwt
	 */
	public String getJwt() {
		return jwt;
	}
	
}
