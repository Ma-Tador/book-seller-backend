package com.udemy.book_seller.service;

import com.udemy.book_seller.model.User;

public interface AuthenticationService {

	
	User signInAndGetJwt(User signInUser);
}
