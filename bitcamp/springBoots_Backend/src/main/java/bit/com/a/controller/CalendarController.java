package bit.com.a.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bit.com.a.dto.CalendarDto;
import bit.com.a.service.CalendarService;
import bit.com.a.util.Cal;
import bit.com.a.util.CalendarUtil;

@RestController
public class CalendarController {
	
	@Autowired
	CalendarService service;
	
	@RequestMapping(value = "/calendar", method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> calendar(String syear, String smonth, String id) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		int year = cal.get(Calendar.YEAR);
		if(CalendarUtil.nvl(syear) == false){
			year = Integer.parseInt(syear);
		}
		int month = cal.get(Calendar.MONTH) + 1;
		if(CalendarUtil.nvl(smonth) == false){
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
		// 달력 셋팅
		cal.set(year, month - 1, 1);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DATE, lastday);
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		
		List<CalendarDto> list = service.getList(new CalendarDto(id, null, null, year + CalendarUtil.two(month+"") , null));
		
		String cal_top = Cal.cal_top(year, month);
		String cal_main = Cal.cal_main(dayOfWeek, lastday, weekday, year, month, list);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cal_thead", cal_top);
		map.put("cal_tbody", cal_main);
		return map;
	}
	
	@RequestMapping(value = "/calwrite", method = {RequestMethod.GET,RequestMethod.POST})
	public String calwriteAf(CalendarDto dto, String year, String month, String day, String hour, String min) {
		String rdate = year + CalendarUtil.two(month) + CalendarUtil.two(day) + CalendarUtil.two(hour) + CalendarUtil.two(min);
		dto.setRdate(rdate);
		System.out.println(dto.toString());
		String msg = service.addCal(dto);
		
		return msg;
	}
	
	@RequestMapping(value = "/caldetail", method = {RequestMethod.GET,RequestMethod.POST})
	public CalendarDto caldetail(int seq) {
		return service.getCal(seq);
	}
	@RequestMapping(value = "/caldelete", method = {RequestMethod.GET,RequestMethod.POST})
	public String caldelete(int seq) {
		return service.deleteCal(seq);
	}
	@RequestMapping(value = "/calupdate", method = {RequestMethod.GET,RequestMethod.POST})
	public String calupdate(CalendarDto dto, String year, String month, String day, String hour, String min) {
		String rdate = year + CalendarUtil.two(month) + CalendarUtil.two(day) + CalendarUtil.two(hour) + CalendarUtil.two(min);
		dto.setRdate(rdate);
		System.out.println(dto.toString());
		String msg = service.updateCal(dto);
		
		return msg;
	}
}
