package bit.com.a.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CalUtil {
	
	
	// 일수,월수 3 => 03 / 5 => 05 변경함수
	public static String two(String msg){
		return msg.trim().length()<2?"0"+msg.trim():msg.trim();
	}
	
	// null값인지 빈값인지 확인함수
	public static boolean nvl(String msg){
		return msg==null || msg.trim().equals("")?true:false;
	}
	
	// 일정의 제목이 길때 ...으로 처리하는 함수
	public static String dot3(String msg){
		String str = "";
		if(msg.length() >= 7){
			str = msg.substring(0, 7);
			str += "...";
		}else{
			str = msg.trim();
		}
		return str;
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
