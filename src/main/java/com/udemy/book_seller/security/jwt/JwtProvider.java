package com.udemy.book_seller.security.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.udemy.book_seller.security.basic.CustomUserDetails;

public interface JwtProvider {

	
	public Authentication getAuthentication(HttpServletRequest request);
	
	public boolean validateJwt(HttpServletRequest request);
	
	public String generateToken(CustomUserDetails auth);
}
