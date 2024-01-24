package com.code.javamongodb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.javamongodb.dto.GenericResponse;
import com.code.javamongodb.model.BookModel;
import com.code.javamongodb.service.BooksService;

@RestController
@RequestMapping("/library")
public class BooksController {
	
	@Autowired
	private BooksService booksService;	
	
	@PostMapping("/addBook")
	public GenericResponse<String> saveBook(@RequestBody List<BookModel> book) {
		String message = "";
		for(BookModel b : book) {
			try {			
				booksService.saveBook(b);
				message = message + "Se guardo el Libro:" + b.getName() + "\n";			
			} catch (Exception e) {
				e.printStackTrace();
				message = message + "No se guardo el Libro:" + b.getName() + "\n";
			}
		}
		
		return GenericResponse.<String>builder()
		          .success(Boolean.TRUE)
		          .message("Successfull!")
		          .statuscode(200)
		          .data(message)
		          .build();
	}
	
	@GetMapping("/findAllBooks")
	public GenericResponse<List<BookModel>> getBooks(){
		List<BookModel> list = null;
		try {
			list = booksService.getAllBooks();
			return GenericResponse.<List<BookModel>>builder()
			          .success(Boolean.TRUE)
			          .message("Successfull!")
			          .statuscode(200)
			          .data(list)
			          .build();
		} catch (Exception e) {
			e.printStackTrace();
			return GenericResponse.<List<BookModel>>builder()
			          .success(Boolean.FALSE)
			          .message("Invalid")
			          .statuscode(500)
			          .data(list)
			          .build();
		}
	}
	
	@GetMapping("/findAllBooks/{id}")
	public GenericResponse<Optional<BookModel>> getBook(@PathVariable(value="id") int id){
		Optional<BookModel> list = null;
		try {
			list = booksService.findBooksById(id);
			return GenericResponse.<Optional<BookModel>>builder()
			          .success(Boolean.TRUE)
			          .message("Successfull!")
			          .statuscode(200)
			          .data(list)
			          .build();
		} catch (Exception e) {
			e.printStackTrace();
			return GenericResponse.<Optional<BookModel>>builder()
			          .success(Boolean.FALSE)
			          .message("Invalid")
			          .statuscode(500)
			          .data(list)
			          .build();
		}
		
	}
	
	@DeleteMapping("/delete/{id}")
	public GenericResponse<String> deleteBook(@PathVariable(value="id") int id) {
		try {
			booksService.deleteById(id);
			return GenericResponse.<String>builder()
			          .success(Boolean.TRUE)
			          .message("Successfull!")
			          .statuscode(200)
			          .data("Se ha eliminado el libro con id: " + id)
			          .build();
		} catch (Exception e) {
			e.printStackTrace();
			return GenericResponse.<String>builder()
			          .success(Boolean.FALSE)
			          .message("Invalid")
			          .statuscode(500)
			          .data("No se ha eliminado el libro con id: " + id)
			          .build();
		}
	}
}	


