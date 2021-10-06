package bit.com.a.util;

import java.util.List;

import bit.com.a.dto.CalendarDto;

public class Cal {
	//달력 제작
	public static String cal_main(int dayOfWeek, int lastday, int weekday, int year, int month, List<CalendarDto> list) {
		String tbody = "";
		
			   tbody += "<tbody>";
			   tbody += "<tr height='100' align='left' valign='top'>";
				   for(int i=1; i<dayOfWeek; i++) {
					   tbody += "<td style='background-color: #ad9d52'>&nbsp;</td>";	   
				   }
				   for(int i=1; i<=lastday; i++) {
					   if((i + dayOfWeek - 1) % 7 == 1){
						   tbody += "<td style='background-color: #BDBDBD; text-align: left; vertical-align: top;'>";
						   tbody += CalendarUtil.callist(year, month, i);
						   tbody += CalendarUtil.showPen(year, month, i);
						   tbody += CalendarUtil.makeTable(year, month, i, list);
						   tbody += "</td>";
					   }else {
						   tbody += "<td style='background-color: #E6E6E6; text-align: left; vertical-align: top;'>";
						   tbody += CalendarUtil.callist(year, month, i);
						   tbody += CalendarUtil.showPen(year, month, i);
						   tbody += CalendarUtil.makeTable(year, month, i, list);
						   tbody += "</td>";   
					   }
					   if((i + dayOfWeek - 1) % 7 == 0 && i != lastday){	// 개행
			   tbody +=	"</tr>";
			   tbody +=	"<tr height='100' align='left' valign='top'>";
					   }
				   }
				   for(int i = 0; i < 7 - weekday; i++) {
					   	   tbody += "<td style='background-color: #ad9d52'>&nbsp;</td>";
				   }
			   tbody += "</tr>";   
			   tbody += "</tbody>";	   
				   
		return tbody;
	}
	
	public static String cal_top(int year, int month) {
		String pp = String.format("<a href='calendar.html?%d?%d'><img src='image/left.gif'></a>", 
				year-1, month);
		String p = String.format("<a href='calendar.html?%d?%d'><img src='image/prec.gif'></a>", 
				year, month-1);
		String n = String.format("<a href='calendar.html?%d?%d'><img src='image/next.gif'></a>", 
				year, month+1);
		String nn = String.format("<a href='calendar.html?%d?%d'><img src='image/last.gif'></a>", 
				year+1, month);
		
		String str = "<thead>" +
							"<tr>" +
								"<td colspan='7' align='center' style='padding-top: 20px;'>" +
									pp + "&nbsp;&nbsp;" + p + "&nbsp;&nbsp;" +
									"<font color='black' style='font-size: 35px; font-weight: bold;'>" +
										String.format("%d년&nbsp;&nbsp;%d월", year, month) +
									"</font>" +
									n + "&nbsp;&nbsp;" + nn + "&nbsp;&nbsp;" +
								"</td>" +
							"</tr>" +
							"<tr height='50' style='background-color: #58FA58'>" +
								"<th class='days3'>일</th>" +
								"<th class='days3'>월</th>" +
								"<th class='days3'>화</th>" +
								"<th class='days3'>수</th>" +
								"<th class='days3'>목</th>" +
								"<th class='days3'>금</th>" +
								"<th class='days3'>토</th>" +
							"<tr>" +
					  "</thead>";
		return str;
	}
	
}
