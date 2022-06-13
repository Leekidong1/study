package com.web.demo;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.web.demo.filter.JwtFilter;
import com.web.demo.filter.JwtFilter2;

@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<JwtFilter> filter1() {
		FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<>(new JwtFilter());
		bean.addUrlPatterns("/*");
		bean.setOrder(0); // 첫번째 실행
		return bean;
	}
	
	@Bean
	public FilterRegistrationBean<JwtFilter2> filter2() {
		FilterRegistrationBean<JwtFilter2> bean = new FilterRegistrationBean<>(new JwtFilter2());
		bean.addUrlPatterns("/*");
		bean.setOrder(1); // 두번째 실행
		return bean;
	}
}
