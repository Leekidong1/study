<%@page import="dto.MemberDto"%>
<%@page import="dao.BbsDao"%>
<%@page import="dto.BbsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
int seq = Integer.parseInt(request.getParameter("seq")); 
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
<%
BbsDao dao = BbsDao.getInstance();
BbsDto dto = dao.getBbs(seq);
%>
<div align="center">
	<table border="1">
		<tr>
			<th>작성자</th>
			<td>
				<input type="text" value="<%=dto.getId() %>" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getTitle() %></td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><%=dto.getWdate() %></td>
		</tr>
		<tr>
			<th>정보</th>
			<td><%=dto.getRef() %>-<%=dto.getSetp() %>-<%=dto.getDepth() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
			<textarea rows="15" cols="90" readonly="readonly"><%=dto.getContent() %></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
			<br>
			<button type="button" onclick="location.href='bbslist.jsp'">글목록</button>
		
			</td>
		</tr>
	</table>
</div>

<script type="text/javascript">
function answerBbs( seq ) {
	location.href = "detail_bbsAnswer.jsp?seq=" + seq;
}
function updateBbs( seq ) {
	location.href = "detail_bbsUpdate.jsp?seq=" + seq;
}
function deleteBbs( seq ) {
	location.href = "detail_bbsDelete.jsp?seq=" + seq;
}

</script>
</body>
</html>