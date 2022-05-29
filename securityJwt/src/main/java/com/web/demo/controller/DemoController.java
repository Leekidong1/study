package com.web.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
	
	private static final Logger log = LoggerFactory.getLogger(DemoController.class);
	
	@RequestMapping("/")
	public String home() {
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
