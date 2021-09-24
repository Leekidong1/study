<%@page import="bit.com.a.dto.MemberDto"%>
<%@page import="bit.com.a.dto.BbsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
BbsDto bbs = (BbsDto)request.getAttribute("bbs");
MemberDto mem = (MemberDto)session.getAttribute("login");
if(mem == null){
%>
	<script type="text/javascript">
		alert("로그인 해 주십시오");
		location.href = "login.do";
	</script>
<%
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
<h2 style="font-size: 20px;">본문</h2>
<table class="list_table" style="width: 85%" id="_list_table">
<col width="200"><col width="500">
	<tr>
		<th>작성자</th>
		<td style="text-align: left"><%=bbs.getId() %></td>	
	</tr>
	<tr>
		<th>제목</th>
		<td style="text-align: left"><%=bbs.getTitle() %></td>	
	</tr>
	<tr>
		<th>작성일</th>
		<td style="text-align: left"><%=bbs.getWdate() %></td>	
	</tr>
	<tr>
		<th>내용</th>
		<td style="text-align: left">
			<textarea rows="10" cols="50" readonly><%=bbs.getContent() %></textarea>
		</td>	
	</tr>
</table>

</div>


<div align="center">
<h2 style="font-size: 20px;">답글</h2>
<form action="answerAf.do" method="post">
<input type="hidden" name="seq" value="<%=bbs.getSeq() %>">
	<table class="list_table" style="width: 85%" id="_list_table">
	<col width="200"><col width="500">
		<tr>
			<th>아이디</th>
			<td style="text-align: left">
				<input type="text" name="id" id="id" value="<%=mem.getId() %>" readonly>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td style="text-align: left">
				<input type="text" name="title" size="50px">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td style="text-align: left">
				<textarea rows="20" cols="50" name="content"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<span class="button blue">	
					<input type="submit" value="댓글작성">
				</span>
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>