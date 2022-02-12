package com.udemy.book_seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.book_seller.security.basic.CustomUserDetails;
import com.udemy.book_seller.security.basic.CustomUserDetailsService;
import com.udemy.book_seller.service.UserService;

@RestController
@RequestMapping("/api/internal")
public class InternalController {

	@Autowired
	private UserService userService;
	
	@PutMapping("make-admin/{username}")
	public ResponseEntity<?> makeAdmin(@PathVariable("username") String username){
		this.userService.makeAdmin(username);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
