package com.web.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.demo.auth.PrincipalDetails;
import com.web.demo.model.UserDto;
import com.web.demo.service.UserService;

@RestController
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
	
//	@GetMapping("/user")
//	public @ResponseBody String user() {
//		return "user";
//	}
//	
//	@GetMapping("/admin")
//	public @ResponseBody String admin() {
//		return "admin";
//	}
//	
//	@GetMapping("/manager")
//	public @ResponseBody String manager() {
//		return "manager";
//	}
	
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
		return "???????????? ??????";
	}
	
	/* 1??? ?????? ??????*/
//	@Secured("ADMIN") // ???????????? ???????????? ??????????????? ?????????(WebSecurityConfig  @EnableGlobalMethodSecurity ??????????????? ????????? ????????????)
//	@GetMapping("/info")
//	public @ResponseBody String info() {
//		return "????????????";
//	}
	
	/* ????????? ?????? ??????*/
//	@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
//	@GetMapping("/data")
//	public @ResponseBody String data() {
//		return "???????????????";
//	}
	
	// user, manager, admin????????? ?????? ??????
	@GetMapping("/api/v1/user")
	public @ResponseBody String user(Authentication authentication) {
		PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
		System.out.println("principal : "+principal.getUser().getUsername());
		return "user";
	}
	
	// manager, admin????????? ?????? ??????
	@GetMapping("/api/v1/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	
	// admin????????? ?????? ??????
	@GetMapping("/api/v1/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
}
