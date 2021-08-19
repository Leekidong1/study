package bit.com.a.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dto.CalendarDto;
import bit.com.a.dto.SearchDto;

@Repository
public class CalendarDaoImpl implements CalendarDao {
	
	@Autowired
	SqlSession session;
	
	String ns = "Calendar.";

	@Override
	public List<CalendarDto> getCalendarList(CalendarDto dto) {
		System.out.println("CalendarDaoImpl :" +dto.toString());
		List<CalendarDto> list = session.selectList(ns + "getList", dto);
		return list;
	}

	@Override
	public int addCal(CalendarDto dto) {
		int conut = session.insert(ns + "addCal", dto);
		return conut;
	}

	@Override
	public CalendarDto getCal(int seq) {
		CalendarDto dto = session.selectOne(ns + "getCal", seq);
		return dto;
	}

	@Override
	public int updateCal(CalendarDto dto) {
		int count = session.update(ns + "updateCal", dto);
		return count;
	}

	@Override
	public int deleteCal(int seq) {
		int count = session.delete(ns + "deleteCal", seq);
		return count;
	}

	@Override
	public List<CalendarDto> getDateList(CalendarDto dto) {
		List<CalendarDto> list = session.selectList(ns + "getDateList", dto);
		return list;
	}

	@Override
	public List<CalendarDto> searchCal(SearchDto search) {
		return session.selectList(ns + "calSearch", search);
	}

	@Override
	public int allCal(SearchDto search) {
		return session.selectOne(ns + "allCal", search);
	}
}
