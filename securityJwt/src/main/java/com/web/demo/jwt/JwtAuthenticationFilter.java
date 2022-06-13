package com.web.demo.jwt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.demo.auth.PrincipalDetails;
import com.web.demo.model.UserDto;

/* Spring Security 에서 UsernamePasswordAuthenticationFilter 가 있음.
 * 사이트주소/login 요청해서 username, password 전송하면 (post)
 * UsernamePasswordAuthenticationFilter 동작함.
 * */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	// /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("JwtAuthenticationFilter attemptAuthentication() : 로그인 시도");
		
		// 1. username, password 받기
		
		/* x-www-form-urlencoded 방식으로 데이터 올 때*/
		try {
//			BufferedReader br = request.getReader();
//			String input = null;
//			while ((input = br.readLine()) != null) {
//				System.out.println(input);
//			}

			/* JSON 방식으로 올때*/
			ObjectMapper mapper = new ObjectMapper();
			UserDto user = mapper.readValue(request.getInputStream(), UserDto.class);
			System.out.println(user);
			
			// 토큰생성
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			
			// PrincipalDetailsSerivce 의 loadUserByUsername() 함수가 실행됨
			Authentication authentication =
					authenticationManager.authenticate(authenticationToken); // manager에서 해당 username있는지 찾고 authentication 전달해줌.
			
			// 로그인이 되었다는 뜻.
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			System.out.println("로그인 완료 : " + principalDetails.getUser().getUsername());
			
			// return 하는 이유는 security가 권환 관리할 수 있게 하기위해서 필요
			// JWT 토큰을 사용하면서 session필요 없음.
			return authentication; // 로그인 됨 -> authentication 인증됬다면 객체가 session영역에 저장됨
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	// attemptAuthentication실행하여 인증이 정상적으로 되었다면 successfulAuthentication 함수가 실행
	// JWT 토큰을 만들어서 request 요청한 사용자에게 JWT토큰을 response함.
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("JwtAuthenticationFilter successfulAuthentication() : 로그인 성공 후");
		
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		
		// RSA 방식은 아니고 Hash암호방식
		String jwtToken = JWT.create()
				.withSubject("customerToken")
				.withExpiresAt(new Date(System.currentTimeMillis()+(60000*10))) // 60000 = 1분
				.withClaim("id", principalDetails.getUser().getId())
				.withClaim("username", principalDetails.getUser().getUsername())
				.sign(Algorithm.HMAC512("secretKey"));
		
		response.addHeader("Authorization", "Bearer " + jwtToken);
	}
}

	
