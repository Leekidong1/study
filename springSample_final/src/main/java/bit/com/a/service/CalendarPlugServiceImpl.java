package bit.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.CalendarPlugDao;
import bit.com.a.dto.CalendarPlugDto;

@Service
public class CalendarPlugServiceImpl implements CalendarPlugService {

	@Autowired
	CalendarPlugDao dao;

	@Override
	public List<CalendarPlugDto> getCalendarPlugList(CalendarPlugDto dto) {
		return dao.getCalendarPlugList(dto);
	}

	@Override
	public boolean addCalPlug(CalendarPlugDto dto) {
		return dao.addCalPlug(dto)>0?true:false;
	}
}
