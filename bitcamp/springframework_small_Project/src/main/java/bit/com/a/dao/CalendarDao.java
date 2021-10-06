package bit.com.a.dao;

import java.util.List;

import bit.com.a.dto.CalendarDto;
import bit.com.a.dto.SearchDto;

public interface CalendarDao {

	public List<CalendarDto> getCalendarList(CalendarDto dto);
	public int addCal(CalendarDto dto);
	public CalendarDto getCal(int seq);
	public int updateCal(CalendarDto dto);
	public int deleteCal(int seq);
	public List<CalendarDto> getDateList(CalendarDto dto);
	public List<CalendarDto> searchCal(SearchDto search);
	public int allCal(SearchDto search);
}
