package bit.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import bit.com.a.dto.CalendarDto;


@Mapper
@Repository
public interface CalendarDao {
	
	public List<CalendarDto> getList(CalendarDto cal);
	public int addCal(CalendarDto cal);
	public CalendarDto getCal(int seq);
	public int deleteCal(int seq);
	public int updateCal(CalendarDto cal);
}
