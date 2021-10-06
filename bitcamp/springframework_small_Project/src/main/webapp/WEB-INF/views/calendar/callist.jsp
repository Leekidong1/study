<%@page import="bit.com.a.dto.CalendarDto"%>
<%@page import="bit.com.a.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="bit.com.a.util.CalUtil"%>
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
%>

 
<%
List<CalendarDto> list = (List<CalendarDto>)request.getAttribute("list");
if(list.size() == 0 && list == null){
%>
	<script type="text/javascript">
		alert("작성된 일정이 없습니다.");
		location.href = 'calendar.do?id=<%=mem.getId()%>';
	</script>
<%
}
%>    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<table class="list_table" style="width: 85%" id="_list_table">
	<col width="100"><col width="200"><col width="100">
	<tr>
		<th>순번</th><th>제목</th><th>시간</th>
	</tr>
	<%
		for(int i=0; i< list.size(); i++){
			CalendarDto cal = list.get(i);
			int hour = Integer.parseInt(cal.getRdate().substring(8, 10));
			int min = Integer.parseInt(cal.getRdate().substring(10));
		
	%>
	<tr>
		<td><%=i+1 %></td>
		<td>
			<a href='caldetail.do?seq=<%=cal.getSeq()%>'>
			<%=cal.getTitle() %>
			</a>
		</td>
		<td>
			<%=hour %>시 <%=min %>분
		</td>
	</tr>
	<%
		}
	%>
</table><br>
	<span class="button blue">
		<input type="button" value="이전" onclick="location.href='calendar.do?id=<%=mem.getId()%>'">
	</span>
</div>
</body>
</html>