<%@page import="dto.BbsDto"%>
<%@page import="dao.BbsDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
MemberDto mem = (MemberDto)session.getAttribute("login");
if(mem == null){
%>  
	<script>
	alert("로그인 해 주십시오");
	location.href = "login.jsp";
	</script>	
<%
}
int g_seq = Integer.parseInt(request.getParameter("g_seq"));
int b_seq = Integer.parseInt(request.getParameter("b_seq"));
BbsDao dao = BbsDao.getInstance();
BbsDto dto = dao.getBbs(b_seq);
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1"> <!-- 0702 추가 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style type="text/css">
th{
	text-align: center;
}
</style>
</head>
<body>
<div align="center">
	<form action="Bbs" method="get">
		<input type="hidden" name="param" value="bbsEditAf">
		<input type="hidden" name="b_seq" value="<%=b_seq %>">
		<input type="hidden" name="g_seq" value="<%=g_seq %>">
		<table class="row-g-3 shadow-sm p-3">
			<col width="100"><col width="700">		
			<tr>
				<td colspan="2" align="center" class="table-secondary"><h4><em>글수정</em></h4></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" size="100%" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" name="id" value="<%=mem.getId()%>" readonly>	
				</td>	
			</tr>
			<tr>
				<th>게시판 목록</th>
				<td>
				<select name="section">
					<option value="free">자유글</option>
					<option value="hello">가입인사</option>
					<option value="notice">공지사항</option>
				</select>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td >
					<input type="text" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" size="100%" name="title" placeholder="제목을 입력하세요" value="<%=dto.getTitle() %>">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td >
					<textarea rows="15" style="width: 100%; border:1;overflow:visible;text-overflow:ellipsis;" name="content" placeholder="내용을 입력하세요"><%=dto.getContent()%></textarea> 
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정하기" class="Btn btn-primary">
				</td>
			</tr>		
		</table>	
	</form>
</div>
</body>
</html>