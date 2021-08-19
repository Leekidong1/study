<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
int seq = Integer.parseInt(request.getParameter("seq")); 
MemberDto dto = (MemberDto)session.getAttribute("login");
if(dto == null){
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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="lib/jquery.raty.js"></script>
<link rel="stylesheet" href="lib/jquery.raty.css">
<style type="text/css">
th{
	text-align: center;
}
</style>
</head>
<body>
<div align="center">
	<form action="ReviewController" method="get">
		<input type="hidden" name="param" value="reviewWriteAf">
		<input type="hidden" name="seq" value="<%=seq %>">
		<table class="row-g-3 shadow-sm p-3">
			<col width="100"><col width="700">
			<tr>
				<td colspan="2" align="center" class="table-secondary"><h4><em>리뷰작성</em></h4></td>
			</tr>
			<tr class="card-title">
				<td align="center"><strong>아이디</strong></td>
				<td><input type="text" size="100%" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" name="id" value="<%=dto.getId()%>" readonly></td>
			</tr>
			<tr>
				<td align="center"><strong>별점</strong></td>
				<td><div name="nScore"></div></td>
			</tr>
			<tr class="card-body">
				<td align="center"><strong>제목</strong></td>
				<td>
					<input type="text" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" size="100%" name="title" placeholder="제목을 입력하세요">
				</td>
			</tr>	
			<tr class="card-body">
				<td align="center"><strong>내용</strong></td>
				<td>
					<textarea rows="15" style="width: 100%; border:1;overflow:visible;text-overflow:ellipsis;" name="content" placeholder="내용을 입력하세요"></textarea>
				</td>
			</tr>
			<tr class="card-footer text-muted">
				<td colspan="2" align="center"><input type="submit" class="btn btn-primary"></td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">

$(document).ready(function() {
	$('div').raty({ path: 'lib/images' });
//	$()
});

</script>
</body>
</html>