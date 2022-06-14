package com.web.demo.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.web.demo.auth.PrincipalDetails;
import com.web.demo.model.UserDto;
import com.web.demo.repository.JpaUserDao;

// security가 filter가지고 있는데 그 필터중에 BasicAuthenticationFilter 라는 것이 있음.
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어 있음.
// 만약에 권한이 인증이 필요한 주소가 아니라면 이 필터를 건너 뛴다.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	private JpaUserDao jpaUserRepository;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
											 JpaUserDao jpaUserRepository) {
		super(authenticationManager);
		this.jpaUserRepository = jpaUserRepository;
	}
	
	// 인증이나 권한이 필요한 주소요청이 있을 때 해당 필터를 타게 됨.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("인증이나 권한이 필요한 주소 요청되어짐.");
		
		String jwtHeader = request.getHeader("Authorization");
		System.out.println("jwtHeader : " + jwtHeader);
		
		// header가 있는지 확인
		if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		
		// JWT 토큰을 검증해서 정상적으로 사용자인지 확인
		String jwtToken = jwtHeader.replace("Bearer ", "");
		
		String username = JWT.require(Algorithm.HMAC512("secretKey"))
							 .build()
							 .verify(jwtToken)
							 .getClaim("username")
							 .asString();
		
		// 서명이 정상적으로 됨.
		if (username != null) {
			UserDto userEntity = jpaUserRepository.findByUsername(username);
			System.out.println(userEntity);
			PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
			
			// Jwt 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
			Authentication authentication = 
					new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());	
			
			// 강제로 security의 session 접근하여 Authentication객체를 저장 
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			// ========== 로그인 완료
		}
		chain.doFilter(request, response);
	}
}
