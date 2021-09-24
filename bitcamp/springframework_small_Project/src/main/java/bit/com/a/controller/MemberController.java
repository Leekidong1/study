package bit.com.a.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.dto.MemberDto;
import bit.com.a.service.MemberService;

@Controller
public class MemberController {
		
	@Autowired
	MemberService service;
	
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() {
		return "login.tiles";
	}
	
	@RequestMapping(value = "loginAf.do", method = RequestMethod.POST)
	public String loginAf(MemberDto mem, HttpServletRequest req) {
		MemberDto dto = service.login(mem);
		if(dto != null && !dto.getId().equals("")) {
			System.out.println(dto.toString());
			req.getSession().setAttribute("login", dto);
			req.getSession().setMaxInactiveInterval(60 * 60 * 24);   // 초 * 분 * 시
			return "redirect:/bbslist.do";
		}
		
		return "redirect:/login.do";
	}
	
	
	@RequestMapping(value = "regi.do", method = RequestMethod.GET)
	public String regi() {
		return "regi.tiles";
	}
	
	@RequestMapping(value = "regiAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String regiAf(MemberDto dto, Model model) {
		String msg = service.addMember(dto);
		String location = "";
		if(msg.equals("Good")) {
			model.addAttribute("msg", "성공적으로 회원가입하셨습니다");
			location = "login.tiles";
		}else {
			model.addAttribute("msg", "회원가입을 실패하셨습니다");
			location = "regi.tiles";
		}
		return location;
	}
	
	@ResponseBody
	@RequestMapping(value = "idcheck.do", method = RequestMethod.GET)
	public String idcheck(String id) {
		String msg = "";
		boolean check = service.idcheck(id);
		if(check == true) {
			msg = "idfound";
		}else {
			msg = "idnotfound";
		}
		return msg;
	}
}
