<%@page import="bit.com.a.dto.CalendarDto"%>
<%@page import="bit.com.a.dto.MemberDto"%>
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
CalendarDto dto = (CalendarDto)request.getAttribute("cal");
String year = dto.getRdate().substring(0, 4);
String month = dto.getRdate().substring(4, 6);
String date = dto.getRdate().substring(6, 8);
String hour = dto.getRdate().substring(8, 10);
String min = dto.getRdate().substring(10);
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<form action="calwriteAf.jsp" method="post">
		<table class="list_table" style="width: 85%" id="_list_table">
		<col width="200"><col width="500">
			<tr>
				<th>아이디</th>
				<td style="text-align: left">
					<%=dto.getId() %>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td style="text-align: left">
					<%=dto.getTitle() %>
				</td>
			</tr>
			<tr>
				<th>일정</th>
				<td style="text-align: left">
				<%=year %>년&nbsp;<%=month %>월&nbsp;<%=date %>일&nbsp;<%=hour %>시&nbsp;<%=min %>분
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td style="text-align: left">
					<textarea rows="20" cols="60" name="content" readonly><%=dto.getContent() %></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<span class="button blue">
						<input type="button" value="이전" onclick="location.href='calendar.do?id=<%=mem.getId()%>'">
					</span>
					<span class="button blue">
						<input type="button" value="수정" onclick="location.href='calupdate.do?seq=<%=dto.getSeq()%>'">
					</span>
					<span class="button blue">
						<input type="button" value="삭제" onclick="location.href='caldel.do?seq=<%=dto.getSeq()%>&id=<%=mem.getId()%>'">
					</span>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>