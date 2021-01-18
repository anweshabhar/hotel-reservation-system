package com.example.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apigateway.filter.GatewayAuthenticationException;
import com.example.apigateway.request.AuthenticateResponse;
import com.example.apigateway.request.LoginUser;
import com.example.apigateway.service.UserServiceImpl;
import com.example.apigateway.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/signup")
	public void saveUser(@RequestBody LoginUser loginUser) {
		userService.save(loginUser);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticateResponse> createAuthenticationToken(@RequestBody LoginUser loginUser) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
		}catch (BadCredentialsException ex) {
			throw new GatewayAuthenticationException(ex.getMessage());
		}
		UserDetails userDetails = userService.loadUserByUsername(loginUser.getUserName());
		String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticateResponse(jwt));
	}

}
