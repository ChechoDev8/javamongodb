package com.code.javamongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.javamongodb.dto.GenericResponse;
import com.code.javamongodb.dto.UserDto;
import com.code.javamongodb.model.UserModel;
import com.code.javamongodb.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
	@Autowired
	private UserService userService;

	//@Autowired
    //private JwtHelper helper;
	
	@PostMapping("/login")
	public GenericResponse<UserDto> getUser(@RequestBody UserModel login){
		UserDto user = new UserDto();
		try {
			//login.setPass(passwordEncoder().encode(login.getPass()));
			user = userService.loadUserByUser(login.getUsername(), login.getPassword());
			/*if(user != null) {
				UserDetails userDetails = User.builder().
		                username(user.getUsername())
		                .password(passwordEncoder().encode(login.getPass())).roles(Role.ADMIN.toString()).
		                build();
				//String token = this.helper.generateToken(userDetails);
				user.setToken(token);
				return GenericResponse.<UserDto>builder()
				          .success(Boolean.TRUE)
				          .message("Successfull!")
				          .statuscode(200)
				          .data(user)
				          .build();
				}
			else {
				return GenericResponse.<UserDto>builder()
				          .success(Boolean.FALSE)
				          .message("Invalid")
				          .statuscode(500)
				          .data(user)
				          .build();
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			return GenericResponse.<UserDto>builder()
			          .success(Boolean.FALSE)
			          .message(e.getMessage())
			          .statuscode(500)
			          .data(user)
			          .build();
		}
		return GenericResponse.<UserDto>builder()
		          .success(Boolean.TRUE)
		          .message("Successfull!")
		          .statuscode(200)
		          .data(user)
		          .build();
	}
	/*
	@PostMapping("/login")
	public String Login(){
		return "Login from public endpoint";
	}*/
	
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
	/*
	@ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
	
	*/
}
