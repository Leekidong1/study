package com.web.demo.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.web.demo.model.UserDto;

/* Security Session에 유저정보를 담기위해서 
 * 유저정보를 UserDetails 형태로 변환해주는 역할
 * */
public class PrincipalDetails implements UserDetails {
	
	private UserDto user; // 콤포지션
	
	public PrincipalDetails(UserDto user) {
		this.user = user;
	}
	
	public UserDto getUser() {
		return user;
	}
	
	public void setUser(UserDto user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { // roles of User
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		user.getRoleList().forEach(r->{
			authorities.add(()->r);
		});
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {	
		// 1년이상 회원이 로그인 안하면 휴먼계정으로 변경하는 로직 작성가능
		// 현재시간 - 로그인시간 => 1년을 초과하면 false 변경
		return true;
	}

}
