package com.udemy.book_seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
//@PropertySource("classpath:application-${spring.profiles.active:default}.prperties")  //path active profile, default= def profile
public class BookSellerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSellerApplication.class, args);
	}
	

}
