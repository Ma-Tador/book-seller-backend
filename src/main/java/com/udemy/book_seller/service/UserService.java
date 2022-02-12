package com.udemy.book_seller.service;

import java.util.Optional;

import com.udemy.book_seller.model.User;

public interface UserService {

	User saveUser(User user);

	Optional<User> findUserByUsername(String username);

	void makeAdmin(String username);

}
