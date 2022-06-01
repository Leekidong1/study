package com.web.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.demo.model.UserDto;
import com.web.demo.repository.JpaUserDao;

// security 설정에서 loginProcessingUrl("/login") 요청이 오면 자동으로 
// UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수 실행

/* Security Login Process
 * 1.Security Login시작
 * 2.DB에서 해당 유저정보 있는지 찾기 (PrincipalDetailsService의 loadUserByUsername 메소드 실행)
 * 3.찾은 유저정보를 PrincipalDetails를 통해서 UserDetails에 저장
 * 4.UserDetails정보를 Authentication에 저장
 * 5.Security Session에 유저정보가 담긴 Authentication정보가 저장
 */
@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	@Autowired
	private JpaUserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		UserDto userEntity = userDao.findByUsername(username);
		if (userEntity != null) {
			return new PrincipalDetails(userEntity);
		} else {
			throw new UsernameNotFoundException("userEntity is null"); 
		}
	}

}
