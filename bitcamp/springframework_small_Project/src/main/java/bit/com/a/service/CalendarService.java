package bit.com.a.service;

import java.util.List;

import bit.com.a.dto.CalendarDto;
import bit.com.a.dto.SearchDto;

public interface CalendarService {

	public List<CalendarDto> getCalendarList(CalendarDto dto);
	public String addCal(CalendarDto dto);
	public CalendarDto getCal(int seq);
	public String updateCal(CalendarDto dto);
	public String deleteCal(int seq);
	public List<CalendarDto> getDateList(CalendarDto dto);
	public List<CalendarDto> searchCal(SearchDto search);
	public int allCal(SearchDto search);
}
