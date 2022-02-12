package com.udemy.book_seller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sun.security.auth.UserPrincipal;
import com.udemy.book_seller.model.User;
import com.udemy.book_seller.security.basic.CustomUserDetails;
import com.udemy.book_seller.security.jwt.JwtProvider;


@Service
public class AuthenticationServiceImpl implements AuthenticationService{	
	//take username and Passw, and returns JWtToken
	//calls LoadByUsername()
	//convert from AuthenticationInfo provided by user, the User object -> via AuthenticationManager (aka AuthenticationProvider)
	//AuthenticationManager is the link betw User and SpringSecurity
		
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	public User signInAndGetJwt(User signInRequest) {
		//convert from User info to User authentication obj
		//if authenticted, Spring should know it	
		Authentication auth = authManager.authenticate(
				//create UsernamePasswordAuthToken
				new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
		);
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		String jwtToken = jwtProvider.generateToken(userDetails);
		//we store JwtToken in User, as @Transient, bec we dont want it stoerd in DB
		User signInUser = userDetails.getUser();
		signInUser.setJwtToken(jwtToken);
		
		return signInUser;
	}
	
}
