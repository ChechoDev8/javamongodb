package com.code.javamongodb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.javamongodb.model.BookModel;
import com.code.javamongodb.repository.BooksRepository;
import com.code.javamongodb.service.BooksService;

@Service
public class BooksServiceImpl implements BooksService{
	
	@Autowired
	private BooksRepository repo;
	
	public void saveBook(BookModel book) throws Exception{
		repo.save(book);
	}

	public List<BookModel> getAllBooks() throws Exception {
		return repo.findAll();
	}

	public Optional<BookModel> findBooksById(int id) throws Exception {
		return repo.findById(id);
	}

	public void deleteById(int id) throws Exception {
		repo.deleteById(id);		
	}
}
