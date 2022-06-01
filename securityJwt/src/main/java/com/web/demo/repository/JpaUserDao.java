package com.web.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.demo.model.UserDto;

// CRUD함수를 JpaRepository가 들고 있음.
// UserDao와 비교했을 때, @Mapper가 없어도 loC됨. 이유는 JpaRepository를 상속했기 때문
public interface JpaUserDao extends JpaRepository<UserDto, Integer> {
	
	/* JPA 메소드 명명규칙 (Jpa query methods 검새해서 명명규칙 공부해야됨)
	 * findBy -> select * from userdto where
	 * Username -> username = ?(parameter)
	 * */
	public UserDto findByUsername(String username);
}
