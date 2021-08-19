<%@page import="bit.com.a.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
MemberDto mem = (MemberDto)session.getAttribute("login");
if(mem == null){
%>
	<script type="text/javascript">
		alert('로그인 해 주세요');
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
</head>
<body>
<table class="list_table" style="width: 400px; margin-bottom: 30px;" >
<colgroup>
	<col width="50"><col width="200">
</colgroup>
	<tr>
		<th>본문</th>
	</tr>
	<tr>
		<th>게시자</th>
		<td style="text-align: left;">${pds.id}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td style="text-align: left;">${pds.title}</td>
	</tr>
	<tr>
		<th>파일명</th>
		<td style="text-align: left;"><img src="image/folder.png" width="18px" height="18px">&nbsp;&nbsp;${pds.filename}</td>
	</tr>
	<tr>
		<th>등록일</th>
		<td style="text-align: left;">${pds.regdate}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td style="text-align: left;">
			<textarea rows="10" cols="50" readonly="readonly">${pds.content}</textarea>
		</td>
	</tr>
</table>

<form name="frmForm" id="_frmForm" action="pdsanswerAf.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="seq" value="${pds.seq}">
	<table class="list_table" style="width: 400px">
		<tr>
			<th>댓글달기</th>
		</tr>
		<tr>
			<th>아이디</th>
			<td style="text-align: left;">
				<input type="text" name="id" readonly="readonly" value="${login.id}" size="50">
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td style="text-align: left;">
				<input type="text" name="title" size="50" id="title">
			</td>
		</tr>
		<tr>
			<th>파일 업로드</th>
			<td style="text-align: left">
				<input type="file" name="fileload" style="width: 315px">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td style="text-align: left;">	<!-- summernote, smart editor - 글자,이미지 같이 올리는 기능 (구글찾아볼것) -->
				<textarea rows="10" cols="50" name="content" id="content"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="height: 50px; text-align: center;">
				<a href="#none" id="_btnPds" title="댓글달기">
					<img alt="" src="image/bwrite.png">
				</a>
			</td>
		</tr>
	</table>
</form>

<script>
$("#_btnPds").click(function() {
	
	//빈칸체크
	if($("#title").val() == '' || $("#content").val() == ''){
		if($("#title").val() == ''){
			alert('제목 작성해주세요');	
		}
		if($("#content").val() == ''){
			alert('내용 작성해주세요');
		}
		return
	}
	
	$("#_frmForm").submit();
});

</script>
</body>
</html>