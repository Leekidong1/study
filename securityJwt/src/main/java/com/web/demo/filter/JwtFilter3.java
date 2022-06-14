package com.web.demo.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtFilter3 implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
	
		if ("POST".equals(req.getMethod())) {
			String headerAuth = req.getHeader("Authorization");
			System.out.println(headerAuth);
			
			if ("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjdXN0b21lclRva2VuIiwiaWQiOjEsImV4cCI6MTY1NTIxMDExOCwidXNlcm5hbWUiOiJ0ZXN0In0.hhcEQXMvNIlscNkVxriZsTT_sCCLuCyn7BysCBMiF_jI1z0r-5PzXJmV0HzjN4hMQxi5jWnubnq415dAk2zxPA".equals(headerAuth)) {
				chain.doFilter(req, res);
			} else {
				PrintWriter msg = res.getWriter();
				msg.println("인증안됨");
			}
		}
		System.out.println("필터3");
	}
	
	
}
