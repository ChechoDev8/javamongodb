package com.code.javamongodb.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.code.javamongodb.dto.UserDto;
import com.code.javamongodb.jwt.JwtService;
import com.code.javamongodb.model.UserModel;
import com.code.javamongodb.repository.UserRepository;
import com.code.javamongodb.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
	@Autowired
	private UserRepository userRepository;
    
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
    public UserDto loadUserByUser(String username, String pass) throws Exception{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, pass));
		UserModel userModel = userRepository.findByUser(username);		
    	UserDto userEntity = modelMapper.map(userModel, UserDto.class);
    	String token = jwtService.getToken(userModel);
    	userEntity.setToken(token);
        return userEntity;
    }

	@Override
	public void createNewUser(UserModel user) throws Exception {
		userRepository.save(user);		
	}
	
	
}
