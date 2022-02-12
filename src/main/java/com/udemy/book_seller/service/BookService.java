package com.udemy.book_seller.service;

import java.util.List;

import com.udemy.book_seller.model.Book;

public interface BookService {

	Book saveBook(Book book);

	void deleteBook(long bookId);

	List<Book> findAllBooks();

}
