package com.web.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.demo.model.UserDto;
import com.web.demo.service.UserService;

@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	UserService service;
	
	
	@RequestMapping("/")
	public String home() {
		for( UserDto user : service.selectList()) {
			System.out.println(user.toString());
		}
		return "home";
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@RequestMapping("/test")
	public String test(Model model) {
		log.info("==========DemoController test START=============");
		
		model.addAttribute("value", "success!!");
		return "test/testpage";
	}
	
	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		log.info("==========DemoController loginForm Trainsit=============");
		return "loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute UserDto user) {
		service.saveUser(user);
		return "redirect:/loginForm";
	}
	
	@GetMapping("/joinProc")
	public @ResponseBody String joinProc() {
		return "회원가입 완료";
	}
	
	/* 1개 권한 허용*/
	@Secured("ADMIN") // 권한있는 사용자만 사용가능한 메소드(WebSecurityConfig  @EnableGlobalMethodSecurity 활성화되어 있어야 사용가능)
	@GetMapping("/info")
	public @ResponseBody String info() {
		return "개인정보";
	}
	
	/* 여러개 권한 허용*/
	@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	@GetMapping("/data")
	public @ResponseBody String data() {
		return "데이터정보";
	}
	
}
