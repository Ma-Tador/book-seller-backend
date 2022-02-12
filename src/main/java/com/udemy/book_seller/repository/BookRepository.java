package com.udemy.book_seller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.book_seller.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	
}
