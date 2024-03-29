package com.code.javamongodb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	private String username;
	private String email;
	private String role;
	private String token;
}
