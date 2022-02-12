package com.udemy.book_seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.book_seller.model.User;
import com.udemy.book_seller.service.AuthenticationService;
import com.udemy.book_seller.service.UserService;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody User user){
		if(userService.findUserByUsername(user.getUsername()).isPresent()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);	
	}
	
	
	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(@RequestBody User user){
		return new ResponseEntity<>(authService.signInAndGetJwt(user), HttpStatus.OK);
	}
}
