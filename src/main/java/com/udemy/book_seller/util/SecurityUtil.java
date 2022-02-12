package com.udemy.book_seller.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;



public class SecurityUtil {
	
	public static final String ROLE_PREFIX = "ROLE_";
	public static final String AUTH_HEADER = "authorization";
	public static final String AUTH_TOKEN_TYPE = "Bearer";
	public static final String AUTH_TOKEN_PREFIX = AUTH_TOKEN_TYPE + " ";
	
	public static GrantedAuthority convertToSimpleGrantedAuthority(String role) {
		String formattedRole = role.startsWith(ROLE_PREFIX) ? role : ROLE_PREFIX + role;  //need format ROLE_USER
		return new SimpleGrantedAuthority(formattedRole);
	}
	
	
	public static String extractAuthTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTH_HEADER);
		
		if(StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX)) {
			return bearerToken.substring(7); //we delete first 7 chars, wehe "Bearer " written, and let the other part of JWT
		}
		return null;
	}

}
