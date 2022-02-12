package com.udemy.book_seller.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.udemy.book_seller.model.Role;
import com.udemy.book_seller.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);
	
	@Modifying
	@Query("update User set role = :role where username = :username")
	void updateRole(@Param("role") Role role, @Param("username") String username);
	
}
