package com.web.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.demo.model.UserDto;
import com.web.demo.service.UserService;

@Controller
public class DemoController {
	
	private static final Logger log = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	UserService service;
	
	
	@RequestMapping("/")
	public String home() {
		System.err.println("들어왔어");
		
		for( UserDto user : service.selectList()) {
			System.out.println(user.toString());
		}
		return "home";
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@RequestMapping("/login/form")
	public String login() {
		log.info("==========DemoController login START=============");
		return "login";
	}
	
	@RequestMapping("/test")
	public String test(Model model) {
		log.info("==========DemoController test START=============");
		
		model.addAttribute("value", "success!!");
		return "test/testpage";
	}
}
