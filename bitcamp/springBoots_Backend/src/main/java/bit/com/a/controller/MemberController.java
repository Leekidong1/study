package bit.com.a.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bit.com.a.dto.MemberDto;
import bit.com.a.service.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@RequestMapping(value = "/idcheck", method = RequestMethod.POST)
	public String idcheck(String id) {	
		System.out.println("MemberController idcheck()" + new Date());
		
		String msg = service.idcheck(id);
		
		return msg;
	}
	
	@RequestMapping(value = "/regi", method = RequestMethod.POST)
	public String regi(MemberDto mem) {
		System.out.println("MemberController regi()" + new Date());
		
		String msg = service.regi(mem);
		
		return msg;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public MemberDto login(MemberDto mem, HttpSession session) {
		System.out.println("MemberController login()" + new Date());
		System.out.println(mem.toString());	
		MemberDto logininfo = service.login(mem);
		if(logininfo != null) {
			System.out.println(logininfo.getId());
		}
		return logininfo;
	}
}
