package bit.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.CalendarDao;
import bit.com.a.dto.CalendarDto;
import bit.com.a.dto.SearchDto;

@Service
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	CalendarDao dao;

	@Override
	public List<CalendarDto> getCalendarList(CalendarDto dto) {	
		return dao.getCalendarList(dto);
	}

	@Override
	public String addCal(CalendarDto dto) {
		return dao.addCal(dto)>0?"YES":"NO";
	}

	@Override
	public CalendarDto getCal(int seq) {
		return dao.getCal(seq);
	}

	@Override
	public String updateCal(CalendarDto dto) {
		return dao.updateCal(dto)>0?"성공적으로 일정수정되었습니다":"일정수정 실패하였습니다";
	}

	@Override
	public String deleteCal(int seq) {
		return dao.deleteCal(seq)>0?"일정삭제되었습니다":"일정삭제 실패하였습니다";
	}

	@Override
	public List<CalendarDto> getDateList(CalendarDto dto) {
		return dao.getDateList(dto);
	}

	@Override
	public List<CalendarDto> searchCal(SearchDto search) {
		return dao.searchCal(search);
	}

	@Override
	public int allCal(SearchDto search) {
		return dao.allCal(search);
	}
}
