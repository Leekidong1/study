package bit.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bit.com.a.dao.CalendarDao;
import bit.com.a.dto.CalendarDto;

@Service
@Transactional
public class CalendarService {
	
	@Autowired
	CalendarDao dao;
	
	public List<CalendarDto> getList(CalendarDto cal){
		return dao.getList(cal);
	}
	public String addCal(CalendarDto cal) {
		return dao.addCal(cal)>0?"success":"fail";
	}
	public CalendarDto getCal(int seq) {
		return dao.getCal(seq);
	}
	public String deleteCal(int seq) {
		return dao.deleteCal(seq)>0?"success":"fail";
	}
	public String updateCal(CalendarDto cal) {
		return dao.updateCal(cal)>0?"success":"fail";
	}
}
