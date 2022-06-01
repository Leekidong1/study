package com.web.demo.service;

import java.util.List;

import com.web.demo.model.UserDto;

public interface UserService {
	
	public List<UserDto> selectList();
	
	public void saveUser(UserDto user);
}
