<%@page import="dao.MeetingDao"%>
<%@page import="dto.MeetingDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
int Gseq = Integer.parseInt(request.getParameter("Gseq"));
int Mseq = Integer.parseInt(request.getParameter("Mseq"));
MeetingDao dao = MeetingDao.getInstance();
MeetingDto dto = dao.getMeeting(Mseq);
%>    

<%!
// Date => String로 바꿔주는 함수 : 202106211611 -> 2021년 6월 21일 16시 11분
public String toDates(String mdate){

	//202106211611 => 2021-06-21 16:11
	String s = mdate.substring(0, 4) + "년" // yyyy
			 + mdate.substring(4, 6) + "월" // mm
			 + mdate.substring(6, 8) + "일" // dd
			 + mdate.substring(8, 10) + "시" // hh
			 + mdate.substring(10, 12) + "분"; // mm:ss
	return s;
}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
th{
	padding: 10px;
}
td{
	width: 300px;
	padding: 10px;
}
</style>
</head>
<body>
<div align="center">
	<table border="1">
		<tr>
			<th>카테고리</th>
			<td>
				<%=dto.getCategory() %>
			</td>
		</tr>
		<tr>
			<th>지역</th>
			<td>
				<%=dto.getLocation() %>
			</td>
		</tr>
		<tr>
			<th>시작일</th>
			<td>
				<%=toDates(dto.getSdate()) %>
			</td>
		</tr>
		<tr>
			<th>종료일</th>
			<td>
				<%=toDates(dto.getEdate()) %>
			</td>
		</tr>
		<tr>
			<th>정원</th>
			<td>
				<%=dto.getMax_mem() %>
			</td>
		</tr>
		<tr>
			<th>일정제목</th>
			<td>
				<%=dto.getTitle() %>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<%=dto.getContent() %>
			</td>
		</tr>
	</table>
<button type="button" onclick="location.href='mypage_manageGroup_detail.jsp?seq=<%=Gseq%>'">이전으로</button>
<button type="button" onclick="location.href='mypage_meeting_update.jsp?seq=<%=Mseq%>'">수정하기</button>
<button type="button" onclick="location.href='meet?param=deleteMeeting&seq=<%=Mseq%>'">삭제하기</button>
</div>
</body>
</html>