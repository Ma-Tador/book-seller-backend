package com.udemy.book_seller.security.jwt;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.udemy.book_seller.security.basic.CustomUserDetails;
import com.udemy.book_seller.util.SecurityUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtProviderImpl implements JwtProvider{

	@Value("${app.jwt.secret}")
	private String JWT_SECRET;
	
	@Value("${app.jwt.expiration-in-ms}")
	private long JWT_EXPIRATION;
	
	
	@Override
	public String generateToken(CustomUserDetails auth) {
		String authorities = auth.getAuthorities()
				.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")); //set what roles can a user have
		//build a token with the info recv from user
		return Jwts.builder()
				.setSubject(auth.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
				.claim("userId", auth.getId())
				.claim("roles", authorities)
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
				.compact();
	}
	
	@Override
	public boolean validateJwt(HttpServletRequest request) {
		Claims claims = extractClaims(request);
		if(claims == null) {
			return false;
		}
		if(claims.getExpiration().before(new Date())) {  //if expired before now
			return false;// jwt already expired
		}
		return true; //otherwise return true
	}
	
	
	@Override
	public Authentication getAuthentication(HttpServletRequest request) {
		Claims claims = extractClaims(request);
		if(claims == null) {
			return null;
		}
		String username = claims.getSubject();
		Long userId = claims.get("userId", Long.class);
		//make sure authorities aka ROLES are precedeed by ROLE_ADMIN, ROLE_USER etc
		Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
											.map(SecurityUtil::convertToSimpleGrantedAuthority).collect(Collectors.toSet()); 
		CustomUserDetails userDetails = new CustomUserDetails();
		userDetails.setId(userId);
		userDetails.setUsername(username);
		userDetails.setAuthorities(authorities);
		
		if(username == null) {
			return null;
		}
		//to create Authentication Object, we use username+password authentication token
		return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
	}
	
	//from own created SecurityUtil
	private Claims extractClaims(HttpServletRequest request) {
		String jwt = SecurityUtil.extractAuthTokenFromRequest(request);
		
		if(jwt == null) {
			return null; //not a valid jwt
		}
		//else if a valid jwt, extract ROLE of user
		return Jwts.parser() 
							.setSigningKey(JWT_SECRET)
							.parseClaimsJws(jwt)
							.getBody();
	}
	
	
}
