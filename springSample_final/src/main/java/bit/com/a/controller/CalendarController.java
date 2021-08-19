package bit.com.a.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.dto.BbsDto;
import bit.com.a.dto.CalendarDto;
import bit.com.a.dto.MemberDto;
import bit.com.a.dto.SearchDto;
import bit.com.a.service.CalendarService;
import bit.com.a.util.CalUtil;

@Controller
public class CalendarController {
	
	@Autowired
	CalendarService service;
	
	@RequestMapping(value = "calendar.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String calendar(String syear, String smonth, String msg, Model model, HttpSession session) {
		MemberDto dto = (MemberDto)session.getAttribute("login");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		int year = cal.get(Calendar.YEAR);
		if(CalUtil.nvl(syear) == false){
			year = Integer.parseInt(syear);
		}
		int month = cal.get(Calendar.MONTH) + 1;
		if(CalUtil.nvl(smonth) == false){
			month = Integer.parseInt(smonth);
		}
		if(month < 1){
			month = 12;
			year--;
		}
		if(month > 12){
			month = 1;
			year++;
		}
		
		List<CalendarDto> list = service.getCalendarList(new CalendarDto(dto.getId(), null, null, year + CalUtil.two(month+"") , null));
		model.addAttribute("doc_title", "일정달력");
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("list", list);
		model.addAttribute("msg", msg);
		
		return "calendar.tiles";
	}
	
	@RequestMapping(value = "calwrite.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String calwrite(String year, String month, String date, Model model) {
		Calendar cal = Calendar.getInstance();
		int syear = cal.get(Calendar.YEAR);
		if(CalUtil.nvl(year) == false){
			syear = Integer.parseInt(year);
		}
		int smonth = cal.get(Calendar.MONTH) + 1;
		if(CalUtil.nvl(month) == false){
			smonth = Integer.parseInt(month);
		}
		int sdate = cal.get(Calendar.DATE);
		if(CalUtil.nvl(date) == false){
			sdate = Integer.parseInt(date);
		}
		model.addAttribute("doc_title", "일정추가");
		model.addAttribute("year", syear);
		model.addAttribute("month", smonth);
		model.addAttribute("date", sdate);
		return "calwrite.tiles";
	}
	
	@RequestMapping(value = "calwriteAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String calwriteAf(CalendarDto dto, String year, String month, String date, String hour, String min, Model model ) {
		String rdate = year + CalUtil.two(month) + CalUtil.two(date) + CalUtil.two(hour) + CalUtil.two(min);
		dto.setRdate(rdate);
		String check = service.addCal(dto);
		if(check.equals("YES")) {
			model.addAttribute("msg", "성공적으로 일정추가되었습니다");
			model.addAttribute("id", dto.getId());
		}else {
			model.addAttribute("msg", "일정추가 실패하였습니다");
			model.addAttribute("id", dto.getId());
		}
		return "redirect:/calendar.do";
	}
	
	@RequestMapping(value = "caldetail.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String caldetail(int seq, Model model) {
		CalendarDto dto = service.getCal(seq);
		model.addAttribute("cal", dto);
		model.addAttribute("doc_title", "일정상세정보");
		return "caldetail.tiles";
	}
	
	@RequestMapping(value = "calupdate.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String calupdate(int seq, Model model) {
		CalendarDto dto = service.getCal(seq);
		model.addAttribute("cal", dto);
		model.addAttribute("doc_title", "일정수정정보");
		return "calupdate.tiles";
	}
	
	@RequestMapping(value = "calupdateAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String calupdateAf(CalendarDto dto, String year, String month, String date, String hour, String min, Model model ) {
		String rdate = year + CalUtil.two(month) + CalUtil.two(date) + CalUtil.two(hour) + CalUtil.two(min);
		dto.setRdate(rdate);
		String msg = service.updateCal(dto);
		model.addAttribute("msg", msg);
		model.addAttribute("id", dto.getId());
		return "redirect:/calendar.do";
	}
	
	@RequestMapping(value = "caldel.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String caldel(String id, int seq, Model model) {
		String msg = service.deleteCal(seq);
		model.addAttribute("msg", msg);
		model.addAttribute("id", id);
		return "redirect:/calendar.do";
	}
	
	@RequestMapping(value = "callist.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String callist(String id, String year, String month, String date, Model model) {
		String yyyymmdd = year + CalUtil.two(month) + CalUtil.two(date);
		List<CalendarDto> list = service.getDateList(new CalendarDto(id, null, null, yyyymmdd, null));
		
		model.addAttribute("doc_title", "일정상세");
		model.addAttribute("list", list);
		return "callist.tiles";
	}

	@RequestMapping(value = "calsearch.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String calsearch(Model model) {
		model.addAttribute("doc_title", "일정검색");
		return "calsearch.tiles";
	}
	
	@ResponseBody
	@RequestMapping(value = "calsearchAjax.do", method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> calsearchAjax(Model model, SearchDto search) {
		if(search.getStartDate() != null && search.getEndDate() != null) {
			search.setStartDate(search.getStartDate().replace("-", ""));
			search.setEndDate(search.getEndDate().replace("-", ""));
		}
		System.out.println(search.toString());
		List<CalendarDto> list = service.searchCal(search);	// 일정리스트가져오기
		int totalCount = service.allCal(search);	//일정총수 가져오기
		Map<String, Object> map = new HashMap<>();
		map.put("search", search);
		map.put("totalCount", totalCount);
		map.put("callist", list);
		return map;
	}
}
