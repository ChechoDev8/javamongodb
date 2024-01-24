package com.code.javamongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.javamongodb.dto.GenericResponse;
import com.code.javamongodb.dto.UserDto;
import com.code.javamongodb.model.UserModel;
import com.code.javamongodb.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public GenericResponse<UserDto> getUser(@RequestBody UserModel login){
		UserDto user = new UserDto();
		try {
			user = userService.loadUserByUser(login.getUsername(), login.getPassword());
			return GenericResponse.<UserDto>builder()
			          .success(Boolean.TRUE)
			          .message("Successfull!")
			          .statuscode(200)
			          .data(user)
			          .build();
		} catch (Exception e) {
			e.printStackTrace();
			return GenericResponse.<UserDto>builder()
			          .success(Boolean.FALSE)
			          .message(e.getMessage())
			          .statuscode(500)
			          .data(user)
			          .build();
		}
	}
	
	@PostMapping("/createuser")
	public GenericResponse<String> createNewUser(@RequestBody UserModel login){
		try {
			login.setPassword(passwordEncoder().encode(login.getPassword()));
			userService.createNewUser(login);
			return GenericResponse.<String>builder()
			          .success(Boolean.TRUE)
			          .message("Successfull!")
			          .statuscode(200)
			          .data("Nuevo usuario creado")
			          .build();
		} catch (Exception e) {
			e.printStackTrace();
			return GenericResponse.<String>builder()
			          .success(Boolean.FALSE)
			          .message(e.getMessage())
			          .statuscode(500)
			          .data("Existio un problema al crear nuevo usuario")
			          .build();
		}
	}
	
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
