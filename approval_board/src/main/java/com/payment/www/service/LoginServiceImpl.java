package com.payment.www.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.payment.www.dao.LoginDao;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Resource(name = "loginDao")
	private LoginDao dao;

	@Override
	public Map<String, Object> login(Map<String, Object> map) {
		return dao.login(map);
	}
}
