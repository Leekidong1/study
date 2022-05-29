package com.payment.www.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("loginDao")
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public Map<String, Object> login(Map<String, Object> map) {
		return sqlSession.selectOne("login.loginInfo", map);
	}
	
	
}
