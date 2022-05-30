package com.web.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.demo.model.UserDto;

@Mapper
public interface UserDao {
	
	public List<UserDto> selectList();
}
