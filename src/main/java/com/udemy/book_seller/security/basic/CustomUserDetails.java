package com.udemy.book_seller.security.basic;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.udemy.book_seller.model.Role;
import com.udemy.book_seller.model.User;
import com.udemy.book_seller.util.SecurityUtil;

public class CustomUserDetails implements UserDetails{

	private static final long serialVersionUID = -5886666043915652767L;
	private long id;
	private String username;
	transient private String passwrord; //transient to not serialize (dont save in JSON )
	transient private User user; //used only for login operations, dont use in JWT
	private Set<GrantedAuthority> authorities;
	
	
	public static CustomUserDetails getAdminUser()
	{
		Set<GrantedAuthority> authorities = Set.of(SecurityUtil.convertToSimpleGrantedAuthority(Role.SYSTEM_MANAGER.name()));
		CustomUserDetails admin = new CustomUserDetails();
		admin.setId(-1); //random dummy id
		admin.setUsername("System-Administrator");
		admin.setAuthorities(authorities);
		return admin;
	}	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.passwrord;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public long getId() {
		return id;
	}

	public String getPasswrord() {
		return passwrord;
	}

	public User getUser() {
		return user;
	}

	public CustomUserDetails(long id, String username, String passwrord, User user, Set<GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.passwrord = passwrord;
		this.user = user;
		this.authorities = authorities;
	}
	
	public CustomUserDetails() {}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPasswrord(String passwrord) {
		this.passwrord = passwrord;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	};
	
}
