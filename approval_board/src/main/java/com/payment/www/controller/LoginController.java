package com.payment.www.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.payment.www.service.LoginService;


@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name = "loginService")
	private LoginService service;
	
	@RequestMapping(value = "login")
	public String login() {
		logger.info("Login -> Controller");
		return "login";
	}
	
	@RequestMapping(value = "loginAf", method = RequestMethod.POST)
	public String loginAf(Model model, 
						  @RequestParam Map<String, Object> map, 
						  HttpServletRequest request) {
		logger.info("LoginAf -> Controller");
		Map<String, Object> login = service.login(map); 

		if (login == null) {
			model.addAttribute("msg", "등록되지 않은 사용자입니다.");
			return "login";
		}
		
		if (!map.get("memPwd").toString().equals(login.get("memPwd").toString())) {
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "login";
		}
		
		request.getSession().setAttribute("login", login);
		request.getSession().setMaxInactiveInterval(60 * 60 * 24);
		
		return "list";
	}
	
	@RequestMapping(value = "logout")
	public String logout(HttpServletRequest request) {
		logger.info("logout -> Controller");
		
		request.getSession().invalidate(); // 로그아웃
		
		return "redirect:/login";
	}
}
