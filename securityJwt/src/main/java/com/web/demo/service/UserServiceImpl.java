package com.web.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.demo.model.UserDto;
import com.web.demo.repository.JpaUserDao;
import com.web.demo.repository.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JpaUserDao jpaUserDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public List<UserDto> selectList() {
		return userDao.selectList();
	}

	@Override
	public void saveUser(UserDto user) {
		/* password encryption*/
		String orgPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(orgPassword);
		user.setPassword(encPassword);
		
		user.setRole("USER");
		jpaUserDao.save(user);
	}

}
