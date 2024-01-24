package com.code.javamongodb.service;

import java.util.List;
import java.util.Optional;

import com.code.javamongodb.model.BookModel;

public interface BooksService {
	void saveBook(BookModel book) throws Exception;
	List<BookModel> getAllBooks() throws Exception;
	Optional<BookModel> findBooksById(int id) throws Exception;
	void deleteById(int id) throws Exception;
}
