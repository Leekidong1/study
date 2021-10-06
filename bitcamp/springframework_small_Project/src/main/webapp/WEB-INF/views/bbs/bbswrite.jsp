<%@page import="bit.com.a.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String id = "";
MemberDto mem = (MemberDto)session.getAttribute("login");
if(mem == null){
%>
	<script type="text/javascript">
		alert('로그인 해주세요');
		location.href="login.do";
	</script>
<% 
}else{
	id = mem.getId();
}
String msg = (String)request.getAttribute("msg");
if(msg != null){
%>
	<script type="text/javascript">
		alert('<%=msg%>');
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

<form action="bbswriteAf.do" method="get">
	<table class="list_table" style="width: 85%" id="_list_table">
	<colgroup>
		<col style="width:200px;" />
		<col style="width:auto;" />
	</colgroup>
		<tr>
			<th>아이디</th>
			<td style="text-align: left">
				<input type="text" name="id" id="id" size="30" readonly>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td style="text-align: left">
				<input type="text" name="title" size="30">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td style="text-align: left">
				<textarea rows="20px" cols="80px" name="content"></textarea>
			</td>
		</tr>
	</table>
	<span class="button blue">	
		<input type="submit" value="작성완료">
	</span>
</form>
</div>

<script type="text/javascript">
document.getElementById("id").value = "<%=id %>";

</script>
</body>
</html>