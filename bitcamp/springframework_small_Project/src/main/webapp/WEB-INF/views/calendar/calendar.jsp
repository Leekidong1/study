<%@page import="bit.com.a.util.CalUtil"%>
<%@page import="bit.com.a.dto.CalendarDto"%>
<%@page import="bit.com.a.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%
MemberDto mem = (MemberDto)session.getAttribute("login");
if(mem == null){
%> 
	<script>
	alert("로그인 해 주십시오");
	location.href = "login.do";
	</script>
<%
}
String msg = (String)request.getAttribute("msg");
if(msg != null){
%>
	<script>
	alert('<%=msg%>');
	</script>
<%
}
%>
<!-- 캘런더 함수 -->
<%!
//날짜, 일별일정
public String callist(int year, int month, int date, MemberDto mem){
	String str = "";
	
	str += String.format("&nbsp;<a href='%s?year=%d&month=%d&date=%d&id=%s'>", "callist.do", year, month, date, mem.getId());
	str += String.format("%2d", date);
	str += "</a>";
	return str;
}
//일정추가 이미지
public String showPen(int year, int month, int date){
	String str = "";
	String image = "<img src='image/pen2.png' width='18px' height='18px'>";
	str += String.format("&nbsp;<a href='calwrite.do?year=%d&month=%d&date=%d'>%s</a>", 
						year, month, date, image);
	return str;
}
//날짜별 리스트
public String makeTable(int year, int month, int day, List<CalendarDto> list){
   String str = "";
   
   // 2021 6 18   -> 20210618
   String dates = (year + "") + CalUtil.two(month + "") + CalUtil.two(day + "");
   
   str +="<table>";
      
   for(CalendarDto dto : list){
      if(dto.getRdate().substring(0, 8).equals(dates)){
         str += "<tr><td style='text-align: left'>";
         str += "<a href='caldetail.do?seq=" + dto.getSeq() + "'>";
         if(dto.getImportant().equals("verymuch")){
        	 str += "<font style='font-size:11px; color:white; background-color:red'>"; 
         }else if(dto.getImportant().equals("very")){
        	 str += "<font style='font-size:11px; color:white; background-color:blue'>"; 
         }else{
        	 str += "<font style='font-size:11px; color:white; background-color:green'>"; 
         }
         str += CalUtil.dot3(dto.getTitle());
         str += "</font>";
         str += "</a>";
         str += "</td></tr>";         
      }      
   }      
   str +="</table>";
   
   return str;
}
%>


<%
int year = (int)request.getAttribute("year");
int month = (int)request.getAttribute("month");
List<CalendarDto> list = (List<CalendarDto>)request.getAttribute("list");
Calendar cal = Calendar.getInstance();

cal.set(year, month - 1, 1);

//요일
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

// year --;
String pp = String.format("<a href='calendar.do?syear=%d&smonth=%d'><img src='image/left.gif'></a>", 
							year-1, month);
// month --;
String p = String.format("<a href='calendar.do?syear=%d&smonth=%d&'><img src='image/prec.gif'></a>", 
							year, month-1);
// month ++;
String n = String.format("<a href='calendar.do?syear=%d&smonth=%d&'><img src='image/next.gif'></a>", 
							year, month+1);
// year ++;
String nn = String.format("<a href='calendar.do?syear=%d&smonth=%d&'><img src='image/last.gif'></a>", 
							year+1, month);

%>    
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
a{
	text-decoration: none;
}
</style>
</head>
<body>
<div align="center">
<table class="list_table" style="width: 80%; margin-left: auto; margin-right: auto;" id="_list_table">
<col width="100"><col width="100"><col width="100"><col width="100">
<col width="100"><col width="100"><col width="100">
	<thead>	
		<tr>
			<td colspan="7" align="center" style="padding-top: 20px;">
				<%=pp %>&nbsp;&nbsp;<%=p %>&nbsp;&nbsp;
				
				<font color="black" style="font-size: 35px; font-weight: bold;">
					<%=String.format("%d년&nbsp;&nbsp;%d월", year, month) %>
				</font>
				
				<%=n %>&nbsp;&nbsp;<%=nn %>&nbsp;&nbsp;
			</td>
		</tr>

		<tr height="50" style="background-color: #58FA58">
			<th class="days3">일</th>
			<th class="days3">월</th>
			<th class="days3">화</th>
			<th class="days3">수</th>
			<th class="days3">목</th>
			<th class="days3">금</th>
			<th class="days3">토</th>
		</tr>
	</thead>
	<tbody>
		<tr height="100" align="left" valign="top">
		<%
		//윗쪽 빈칸
		for(int i=1; i<dayOfWeek; i++){
		%>
			<td style="background-color: #ad9d52">&nbsp;</td>
		<% 
		}
		//날짜
		int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for(int i = 1; i <= lastday; i++){
			if((i + dayOfWeek - 1) % 7 == 1){
		%>
			<td style="background-color: #BDBDBD; text-align: left; vertical-align: top;">
				<%=callist(year,month,i,mem) %><%=showPen(year,month,i) %>
				<%=makeTable(year,month,i,list) %>
			</td>
		<%
			}else{
		%>
			<td style="background-color: #E6E6E6; text-align: left; vertical-align: top;">
				<%=callist(year,month,i,mem) %><%=showPen(year,month,i) %>
				<%=makeTable(year,month,i,list) %>
			</td>
		<%	
			}
			if((i + dayOfWeek - 1) % 7 == 0 && i != lastday){	// 개행
			%>
		</tr>
		<tr height="100" align="left" valign="top">
			<%
			}
		}
		//밑의 빈칸
		cal.set(Calendar.DATE, lastday);
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		for(int i = 0; i < 7 - weekday; i++){
		%>
			<td style="background-color: #ad9d52">&nbsp;</td>
		<%
		}
		%>
		</tr>
	</tbody>
</table>
<br>
</div>

</body>
</html>