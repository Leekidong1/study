package com.web.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	
	
	private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		log.info("======Spring Security START=========");
		log.info("======Spring Security START=========");
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/","/home","/css/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login/form")
				.loginProcessingUrl("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();		
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
							   .username("user")
							   .password("password")
							   .roles("USER")
							   .build();
		return new InMemoryUserDetailsManager(user);
	}
	
}
