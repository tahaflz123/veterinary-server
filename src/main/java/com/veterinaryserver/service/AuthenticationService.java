package com.veterinaryserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.veterinaryserver.entity.user.User;

public class AuthenticationService implements UserDetailsService{

	Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Start of loadByUsername, username {}", username);
		User user = this.userService.findByUsername(username);
		logger.info("User, {}", user.toString());
		logger.info("loadByUsername, username, {}", user.getUsername());
		return user;
	}
	
}
