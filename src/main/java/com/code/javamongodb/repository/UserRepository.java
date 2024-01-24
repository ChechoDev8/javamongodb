package com.code.javamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.code.javamongodb.model.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, Integer>{
	@Query(value="{ 'username' : ?0 }", fields="{ 'username' : 1, 'password' : 1, 'email' : 1, 'role' : 1}")
    UserModel findByUser(String username);
}
