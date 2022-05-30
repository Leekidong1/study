package com.web.demo.model;

import lombok.Data;

@Data
public class UserDto {
	
	private String id;
	private String pw;
	private String auth;
	
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", pw=" + pw + ", auth=" + auth + "]";
	}
}
