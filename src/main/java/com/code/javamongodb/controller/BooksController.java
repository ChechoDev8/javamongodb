package com.code.javamongodb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.javamongodb.model.Book;
import com.code.javamongodb.repository.BooksRepository;

@RestController
public class BooksController {
	
	@Autowired
	private BooksRepository repo;
	
	
	@PostMapping("/addBook")
	public String saveBook(@RequestBody List<Book> book) {
		for(Book b : book) {
			repo.save(b);
		}
		
		return "Se guardo el/los libro(s): " + book;
	}
	
	@GetMapping("/findAllBooks")
	public List<Book> getBooks(){
		return repo.findAll();
	}
	
	@GetMapping("/findAllBooks/{id}")
	public Optional<Book> getBook(@PathVariable(value="id") int id){
		return repo.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable(value="id") int id) {
		repo.deleteById(id);
		return "Se ha eliminado el libro con id: " + id;
	}
}	


