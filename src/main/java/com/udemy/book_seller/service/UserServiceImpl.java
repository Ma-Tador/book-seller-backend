package com.udemy.book_seller.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.udemy.book_seller.model.Role;
import com.udemy.book_seller.model.User;
import com.udemy.book_seller.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired //from main BCryptPasswEncod by Type
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.USER);
		user.setCreateTime(LocalDateTime.now());
		return userRepository.save(user);
	}
	
	
	@Override
	public Optional<User> findUserByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	@Override
	@Transactional//because do an Update/Del query on DB, we need transactional, s.t. the update is done only if all update ok
	public void makeAdmin(String username) {
		userRepository.updateRole(Role.ADMIN, username);
	}
	
}
