package com.udemy.book_seller.security.basic;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.udemy.book_seller.model.Role;
import com.udemy.book_seller.model.User;
import com.udemy.book_seller.service.UserService;
import com.udemy.book_seller.util.SecurityUtil;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findUserByUsername(username)
				.orElseThrow( () -> new UsernameNotFoundException(username));
		Set<GrantedAuthority> authorities = Set.of(SecurityUtil.convertToSimpleGrantedAuthority(user.getRole().name()));
		return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), user, authorities);
	}
	
}
