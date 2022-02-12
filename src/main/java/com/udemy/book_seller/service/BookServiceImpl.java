package com.udemy.book_seller.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.book_seller.model.Book;
import com.udemy.book_seller.repository.BookRepository;


@Service
public class BookServiceImpl implements BookService{

	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book saveBook(Book book) {
		book.setCreateDate(LocalDateTime.now());
		return bookRepository.save(book);
	}
	
	@Override
	public void deleteBook(long bookId) {
		bookRepository.deleteById(bookId);
	}
	
	
	@Override
	public List<Book> findAllBooks(){
		return bookRepository.findAll();
	}
	
	
}
