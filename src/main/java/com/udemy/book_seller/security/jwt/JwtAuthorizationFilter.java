package com.udemy.book_seller.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthorizationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtProvider jwtProvider;

	//we filter 1 at beginning, to find out who is the user from DB, then set User in SecurityContext
	//stores req attributes for already filtered, and dont filter again if attribute is already there
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Authentication auth = jwtProvider.getAuthentication(request);
		
		if(auth != null && jwtProvider.validateJwt(request)) {
			//if valid token and auth, then set as authenticated in SecurityContexttHolder
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		//then we forward req and response to other filters in the chain of filters
		//first filter is JwtAuthFilter, then UsernameAndPassw(default Spring form login) filter (in this order)
		//we should filter the req in this order, otherwise we get error if filtering in other order
		//we add order JwtFilter before UsernameAndPassword filter, in class SecurityConfig
		filterChain.doFilter(request, response);	
	}
	
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getRequestURI().startsWith("/api/internal");//should not filter if path req = internal
	}
}
