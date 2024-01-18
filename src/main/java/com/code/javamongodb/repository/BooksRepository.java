package com.code.javamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.code.javamongodb.model.Book;

public interface BooksRepository extends MongoRepository<Book, Integer>{

}
