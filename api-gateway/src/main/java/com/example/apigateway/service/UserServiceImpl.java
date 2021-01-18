package com.example.apigateway.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.apigateway.entity.User;
import com.example.apigateway.repository.UserRepository;
import com.example.apigateway.request.LoginUser;

@Service
public class UserServiceImpl implements UserDetailsService, UserService{
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repo.findByUserName(username);
		if(!user.isPresent()) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.get().getUserName(), user.get().getPassword(), java.util.Collections.emptyList());
	}

	@Override
	public void save(LoginUser user) {
		User newUser = new User();
		newUser.setUserName(user.getUserName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		repo.save(newUser);
	}

}
