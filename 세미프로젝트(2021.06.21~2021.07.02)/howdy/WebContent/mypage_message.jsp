<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String msg = request.getParameter("msg");

if(msg.equals("OK")){
%>
	<script type="text/javascript">
	alert("일정이 추가되었습니다");
	location.href = "mypage_calendar?param=mypage_calendar.jsp";
	</script>
<%
}else{
%>
	<script type="text/javascript">
	alert("다시 입력해 주십시오");
	location.href = "mypage_calendar?param=mypage_calendarWrite.jsp";
	</script>
<%
}
%>


</body>
</html>


