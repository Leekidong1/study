<%@page import="bit.com.a.dto.BbsDto"%>
<%@page import="bit.com.a.dto.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
td{
	text-align: left;
}
</style>
</head>
<body>

<div align="center">
<form action="bbsupdate.do" method="get">
	<input type="hidden" name="seq" value="<%=bbs.getSeq()%>">
	<table class="list_table" style="width: 85%">
	<colgroup>
		<col style="width:200px;" />
		<col style="width:auto;" />
	</colgroup>
		<tr>
			<th>작성자</th>
			<td style="text-align: left">
				<input type="text" name="id" value="<%=bbs.getId() %>" readonly>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td style="text-align: left">
				<input type="text" name="title" value="<%=bbs.getTitle() %>" readonly>
			</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td style="text-align: left">
				<input type="text" name="date" value="<%=bbs.getWdate() %>" readonly>
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td style="text-align: left">
				<input type="text" name="count" value="<%=bbs.getReadcount() %>" readonly>
			</td>
		</tr>
		<tr>
			<th>정보</th>
			<td style="text-align: left">
				<input type="text" name="info" value="<%=bbs.getRef() %>-<%=bbs.getStep() %>-<%=bbs.getDepth() %>" readonly>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td style="text-align: left">
				<textarea rows="20px" cols="80px" name="content" readonly><%=bbs.getContent() %></textarea>
			</td>
		</tr>
	</table>
	<span class="button blue">
		<button type="button" onclick="before()">글목록</button>
	</span>
	<% 
	if(bbs.getId().equals(mem.getId())){
	%>
	<span class="button blue">	
		<input type="submit" value="수정하기">
	</span>
	<span class="button blue">	
		<button type="button" id="btnSearch" onclick="del(<%=bbs.getSeq()%>)">삭제</button>
	</span>
	<%
	}
	%>
	<span class="button blue">	
		<button type="button" onclick="answerBbs(<%=bbs.getSeq()%>)">댓글</button>
	</span>
</form>
</div>	
<script type="text/javascript"> 
function before() {
	location.href ="bbslist.do";
}

function del(seq) {
	location.href ="bbsdelete.do?seq=" + seq;
}
function answerBbs(seq) {
	location.href ="answer.do?seq=" + seq;
}
</script>
</body>
</html>