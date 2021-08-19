package bit.com.a.util;

import java.util.Calendar;
import java.util.Date;

public class PollUtil {
	
	// DATE 달력의 날짜를 20210719 형식으로 만들기
	public static String StringCal(Calendar dd) {
		String s = "";
		int year = dd.get(Calendar.YEAR);
		int month = dd.get(Calendar.MONTH) + 1;
		int day = dd.get(Calendar.DATE);
		
		s = year + CalendarUtil.two(month + "") + CalendarUtil.two(day + "");
		return s;
	}
	
	// 연월일을 비교. 투표가 끝났는지? 오늘 > 종료일 -> 기간만료
	public static boolean isEnd(Date edate) {
		// 종료일 세팅
		Calendar expire = Calendar.getInstance();	// 오늘날짜 얻어오기
		expire.setTime(edate);	// 지정 날짜 세팅해줌
		
		//오늘
		Calendar today = Calendar.getInstance();
		
		// 오늘날짜 > 지정된 날짜(종료일)	->기간만료했다면 true
		boolean b = Integer.parseInt(StringCal(today)) > Integer.parseInt(StringCal(expire));
		return b;
	}
	
	// 투표 종료의 판별
	public static String pollState(Date edate) {
		String s1 = "<div style='color:RED'>[종료]</div>";
		String s2 = "<div style='color:BLUE'>[진행중]</div>";
		
		return isEnd(edate)?s1:s2;
	}
	//순서 : edate(Date) -> edate(Calendar),today(Calendar) 객체생성 -> 
	//      edate(Calendar -> String -> Integer) < today(Calendar -> String -> Integer) -> pollState 메세지출력
}
