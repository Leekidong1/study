package bit.com.a.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dto.CalendarPlugDto;

@Repository
public class CalendarPlugDaoImpl implements CalendarPlugDao {
	
	@Autowired
	SqlSession session;
	
	String ns = "CalPlug.";
	
	@Override
	public List<CalendarPlugDto> getCalendarPlugList(CalendarPlugDto dto) {
		return session.selectList(ns + "getCalendarPlugList",dto);
	}

	@Override
	public int addCalPlug(CalendarPlugDto dto) {
		return session.insert(ns + "addCalPulg", dto);
	}
}
