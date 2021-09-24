package bit.com.a.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.dto.CalendarPlugDto;
import bit.com.a.dto.MemberDto;
import bit.com.a.service.CalendarPlugService;
import bit.com.a.util.CalUtil;

@Controller
public class CalendarPlugController {

	@Autowired
	CalendarPlugService service;
	
	@RequestMapping(value = "calendarpluglist.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String calendarpluglist(Model model, HttpSession session) {
		model.addAttribute("doc_title", "일정목록 plug");
		
		MemberDto dto = (MemberDto)session.getAttribute("login");
		CalendarPlugDto cal = new CalendarPlugDto();
		cal.setId(dto.getId());
		
		List<CalendarPlugDto> list = service.getCalendarPlugList(cal);
		model.addAttribute("callist", list);
		
		return "calendarpluglist.tiles";
	}
	
	@ResponseBody
	@RequestMapping(value = "calendarpluglistAjax.do", method = {RequestMethod.GET,RequestMethod.POST})
	public List<CalendarPlugDto> calendarpluglistAjax(HttpSession session) {
		MemberDto dto = (MemberDto)session.getAttribute("login");
		CalendarPlugDto cal = new CalendarPlugDto();
		cal.setId(dto.getId());
		
		List<CalendarPlugDto> list = service.getCalendarPlugList(cal);
		
		return list;
	}
		
	@RequestMapping(value = "calplugwriteAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String calplugwriteAf(Model model, HttpSession session, CalendarPlugDto cal, String date, String shour, String smin, String ehour, String emin ) {
		String startdate = date + " " + CalUtil.two(shour) +":"+CalUtil.two(smin)+":00";
		String enddate = date + " " + CalUtil.two(ehour) +":"+CalUtil.two(emin)+":00";
		cal.setStartdate(startdate);
		cal.setEnddate(enddate);
		MemberDto dto = (MemberDto)session.getAttribute("login");
		cal.setId(dto.getId());
		boolean b = service.addCalPlug(cal);
		if(b) {
			System.out.println("일정추가성공");
		}else {
			System.out.println("일정추가실패");
		}
		model.addAttribute("doc_title", "일정목록 plug");
		return "redirect:/calendarpluglist.do";
	}	
}
