package com.web.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.web.demo.jwt.CorsConfig;

@Configuration
@EnableWebSecurity // 스피링 시큐리티 필터가 스프링 필터체인에 등록한다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화, preAuthorize 어노테이션 활성화)
public class WebSecurityConfig {
		
	private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Autowired
	private CorsConfig corsConfig;
	
	// 해당 메서드의 리턴되는 오브젝트를 IoC로 등록
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
/*	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		log.info("======Spring Security START=========");
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated()
			.antMatchers("/manager/**").access("hasRole('ADMIN') or hasRole('MANAGER')")	// Global 권한 허용 
			.antMatchers("/admin/**").access("hasRole('ADMIN')")
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/loginForm")
			.loginProcessingUrl("/login") // login 주소가 호출되면 security가 대신 로그인 진행(contoller에서 login 주소호출하는 메소드 필요없음)
										  // PrincipalDetailsService에서 loadUserByUsername메소드 실행
			.defaultSuccessUrl("/");
		return http.build();
	}
*/	
	@Bean
	public SecurityFilterChain configureJwt(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Session 않쓴다는 의미
		.and()
		.addFilter(corsConfig.corsFilter()) // @CrossOrigin(인증X), 시큐리티 필터에 드록 인증(O)
		.formLogin().disable()
		.httpBasic().disable() // 기본인증 방식 사용 X Bearer 인증 방식 사용 예정.
		.authorizeRequests()
		.antMatchers("/api/v1/user/**")
		.access("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
		.antMatchers("/api/v1/manager/**")
		.access("hasRole('MANAGER') or hasRole('ADMIN')")
		.antMatchers("/api/v1/admin/**")
		.access("hasRole('ADMIN')")
		.anyRequest().permitAll();
		
		return http.build();
	}
}
