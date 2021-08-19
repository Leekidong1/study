<%@page import="bit.com.a.dto.BbsDto"%>
<%@page import="bit.com.a.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
BbsDto bbs = (BbsDto)request.getAttribute("bbs");
MemberDto mem = (MemberDto)session.getAttribute("login");
if(mem == null){
	%>
	<script>
	alert("로그인 해 주십시오");
	location.href = "login.jsp";
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
<form action="bbsupdateAf.do" method="get">
	<table class="list_table" style="width: 85%" id="_list_table">
	<colgroup>
		<col style="width:200px;" />
		<col style="width:auto;" />
	</colgroup>
		<tr>
			<th>아이디</th>
			<td style="text-align: left">
				<input type="text" name="id" value="<%=mem.getId()%>" readonly>
				<input type="hidden" name="seq" value="<%=bbs.getSeq()%>">
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td style="text-align: left">
				<input type="text" name="title" value="<%=bbs.getTitle() %>">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td style="text-align: left">
				<textarea rows="20px" cols="80px" name="content"><%=bbs.getContent() %></textarea>
			</td>
		</tr>
	</table>
	<span class="button blue">
		<input type="submit" value="수정완료">
	</span>
</form>
</div>
</body>
</html>