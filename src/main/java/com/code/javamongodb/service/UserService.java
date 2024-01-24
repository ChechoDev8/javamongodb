package com.code.javamongodb.service;


import com.code.javamongodb.dto.UserDto;
import com.code.javamongodb.model.UserModel;

public interface UserService {
	UserDto loadUserByUser(String username, String pass) throws Exception;
	void createNewUser(UserModel user) throws Exception;
}
