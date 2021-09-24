<%@page import="dto.ReviewDto"%>
<%@page import="dao.ReviewDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
int review_seq = Integer.parseInt(request.getParameter("review_seq")); //시퀀스추가
int group_seq = Integer.parseInt(request.getParameter("group_seq")); //시퀀스추가
MemberDto dto = (MemberDto)session.getAttribute("login");
ReviewDao dao = ReviewDao.getInstance();
ReviewDto Rdto = dao.getReview(review_seq);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1"> <!-- 0702  -->
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="lib/jquery.raty.js"></script>
<link rel="stylesheet" href="lib/jquery.raty.css">
</head>
<body>

<form action="ReviewController" method="get">
	<input type="hidden" name="param" value="reviewUpdateAf">
	<input type="hidden" name="review_seq" value="<%=review_seq %>">
	<input type="hidden" name="group_seq" value="<%=group_seq %>">
	<table align="center" class="row-g-3 shadow-sm p-3">
		<col width="300"><col width="500">
		<tr>
			<td colspan="2" align="center" class="table-secondary"><h4><em>리뷰수정</em></h4></td>
		</tr>
		<tr>
			<td align="center"><strong>아이디</strong></td> <!-- 0702 align, strong태그 추가 -->
			<td><input type="text" size="100%" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" name="id" value="<%=dto.getId()%>" readonly></td>
		</tr>
		<tr>
			<td align="center"><strong>별점</strong></td> <!-- 0702 align, strong태그 추가 -->
			<td><div name="nScore"></div></td>
		</tr>
		<tr>
			<td align="center"><strong>제목</strong></td> <!-- 0702 align, strong태그 추가 -->
			<td>
				<input type="text" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" size="100%" name="title" placeholder="제목을 입력하세요"> <!-- 0702 스타일변경 -->
			</td>
		</tr>	
		<tr>
			<td align="center"><strong>내용</strong></td> <!-- 0702 align, strong태그 추가 -->
			<td >
				<textarea rows="15" style="width: 100%; border:1;overflow:visible;text-overflow:ellipsis;" name="content" placeholder="내용을 입력하세요"></textarea> <!-- 0702 스타일변경 -->
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" ><input type="submit" class="Btn btn-primary" ></td>
		</tr>
	</table>
</form>
<script type="text/javascript">

$(document).ready(function() {
	$('div').raty({ path: 'lib/images' });
//	$()
});

</script>
</body>
</html>