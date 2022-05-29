package com.payment.www.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.payment.www.service.ApprService;

@Controller
public class ApprController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApprController.class);
	
	@Resource(name = "apprService")
	private ApprService service;
	
	@ResponseBody
	@RequestMapping(value = "list")
	public Map<String, Object> list(@RequestParam Map<String, Object> map) {
		logger.info("list -> Controller");
		System.out.println(map.toString());
		return service.list(map);
	}
	
	@RequestMapping("apprList")
	public String apprList() {
		return "list";
	}
	
	@RequestMapping(value = "approval")
	public String approval(Model model, @RequestParam Map<String, Object> map) {
		int seq = Integer.parseInt(map.get("seq")+"");
		if (seq == 0) {
			map.put("seq", service.getNextSeq());
		}
		model.addAttribute("apprInfo", map);
		model.addAttribute("list", service.histList(seq));
		return "approval";
	}
	
	@RequestMapping(value = "submitAppr")
	public String submitAppr(@RequestParam Map<String, Object> map) {
		if(service.submitAppr(map)) {
			logger.info("성공하셨습니다");
		} else {
			logger.info("실패하셨습니다");
		}
		return "redirect:/apprList";
	}
}
