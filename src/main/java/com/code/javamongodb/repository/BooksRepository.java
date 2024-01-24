package com.code.javamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.code.javamongodb.model.BookModel;

@Repository
public interface BooksRepository extends MongoRepository<BookModel, Integer>{

}
