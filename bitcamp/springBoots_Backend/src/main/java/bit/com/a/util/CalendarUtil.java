package bit.com.a.util;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import bit.com.a.dto.CalendarDto;

public class CalendarUtil {

	// 날짜를 클릭하면 그날의 일정을 모두 볼 수 있는 callist.jsp로 이동하는 함수
	public static String callist(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		int today = cal.get(Calendar.DATE);
		int thisM = cal.get(Calendar.MONTH)+1;
		String str = "";
		if(day == today && month == thisM) {
			str += String.format("&nbsp;<a href='callist.html?%d?%d?%d' style='background-color: #F4FA58'>", year, month, day);
		}else {
			str += String.format("&nbsp;<a href='callist.html?%d?%d?%d'>", year, month, day);
		}
		str += String.format("%2d", day);
		str += "</a>";

		return str;
	}
	
	// 일정을 추가하기 위해서 pen이미지를 클릭하면 calwrite로 이동하는 함수
	public static String showPen(int year, int month, int day) {
		String str = "";
		String image = "<img src='image/pen2.png' width='18px' height='18px'>";
		str += String.format("&nbsp;<a href='calwrite.html?%d?%d?%d'>%s</a>", 
							year, month, day, image);	
		return str;		
	}
	
	// 달력의 날짜 별로 설정할 테이블을 작성하는 함수
		public static String makeTable(int year, int month, int day, List<CalendarDto> list) {
			String str = "";
			   
			   // 2021 6 18   -> 20210618
			   String dates = (year + "") + CalendarUtil.two(month + "") + CalendarUtil.two(day + "");
			   
			   str +="<table>";
			      
			   for(CalendarDto dto : list){
			      if(dto.getRdate().substring(0, 8).equals(dates)){
			         str += "<tr><td style='text-align: left'>";
			         str += "<a href='caldetail.html?" + dto.getSeq() + "'>";
			         if(dto.getImportant().equals("verymuch")){
			        	 str += "<font style='font-size:11px; color:white; background-color:red'>"; 
			         }else if(dto.getImportant().equals("very")){
			        	 str += "<font style='font-size:11px; color:white; background-color:blue'>"; 
			         }else{
			        	 str += "<font style='font-size:11px; color:white; background-color:green'>"; 
			         }
			         str += CalendarUtil.dot3(dto.getTitle());
			         str += "</font>";
			         str += "</a>";
			         str += "</td></tr>";         
			      }      
			   }      
			   str +="</table>";
			
			return str;
		}
	
	// 한문자를 두문자로 변경해 주는 함수	2021 03 19	-> 1 ~ 9 -> 01 ~ 09
	public static String two(String msg) {
		return msg.trim().length()<2?"0"+msg.trim():msg.trim();
	}
	
	// nvl 함수 : 문자열이 비어 있는지 확인 함수
	public static boolean nvl(String msg) {
		return msg == null || msg.trim().equals("")?true:false;
	}
	
	
	public static String yyyymm(int year, int month) {
		return "" + year + two(month + "");
	}
	
	public static String yyyymmdd(int year, int month, int day) {
		return "" + year + two(month + "") + two(day + "");
	}
	
	public static String yyyymmddhhmm(int year, int month, int day, int hour, int min) {		
		return yyyymmdd(year, month, day) + two(hour + "") + two(min + "");		
	}
	
	// 일정의 제목이 길 때 ...로 처리하는 함수		CGV에서 데이트 약속 -> CGV에서... 
	public static String dot3(String msg) {
		String str = "";
		if(msg.length() >= 7) {
			str = msg.substring(0, 7);
			str += "...";
		}else {
			str = msg.trim();
		}		
		return str;
	}
	
	// 2021-04-26 -> java.sql.Date로 변경
	public static Date toDate(int year, int month, int day) {
		String s = year + "-" + two(month + "") + "-" + two(day + "");
		Date d = Date.valueOf(s);
		return d;
	}
	
	// Date => String로 바꿔주는 함수 : 202106211611 -> 2021년 6월 21일 16시 11분
		public static String toDates(String mdate){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
			
			//202106211611 => 2021-06-21 16:11
			String s = mdate.substring(0, 4) + "-" // yyyy
					 + mdate.substring(4, 6) + "-" // mm
					 + mdate.substring(6, 8) + "-" // dd
					 + mdate.substring(8, 10) + ":" // hh
					 + mdate.substring(10, 12) + ":00"; // mm:ss
			Timestamp d = Timestamp.valueOf(s);
			return sdf.format(d);
		}	
		
}











