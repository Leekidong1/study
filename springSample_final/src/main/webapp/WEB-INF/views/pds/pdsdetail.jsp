<%@page import="bit.com.a.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<table class="list_table" style="width: 50%">
<colgroup>
	<col width="50"><col width="200">
</colgroup>
	<tr>
		<th>게시자</th>
		<td style="text-align: left;">${pds.id}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td style="text-align: left;">${pds.title}</td>
	</tr>
	<c:if test="${pds.filename != 'no'}">
	<tr>
		<th>다운로드</th>
		<td style="text-align: left;">
			<input type="button" name="btnDown" value="다운로드" onclick="filedown('${pds.newfilename}','${pds.seq}','${pds.filename}')">
		</td>
	</tr>
	<tr>
		<th>파일명</th>
		<td style="text-align: left;"><img src="image/folder.png" width="18px" height="18px">&nbsp;&nbsp;${pds.filename}</td>
	</tr>
	<tr>
		<th>다운수</th>
		<td style="text-align: left;">${pds.downcount}</td>
	</tr>
	</c:if>
	<tr>
		<th>조회수</th>
		<td style="text-align: left;">${pds.readcount}</td>
	</tr>
	<tr>
		<th>등록일</th>
		<td style="text-align: left;">${pds.regdate}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td style="text-align: left;">
			<textarea rows="20" cols="50" readonly="readonly">${pds.content}</textarea>
		</td>
	</tr>
</table>
<div id="button.wrap">
	<span class="button blue">
		<button type="button" onclick="location.href='pdslist.do'">돌아가기</button>
	</span>
	<span class="button blue">
		<button type="button" onclick="location.href='pdsupdate.do?seq=${pds.seq}'">수정하기</button>
	</span>
	<span class="button blue">
		<button type="button" onclick="location.href='pdsanswer.do?seq=${pds.seq}'">댓글달기</button>
	</span>
</div>

<form name="file_Down" action="fileDownload.do" method="post">
	<input type="hidden" name="newfilename">
	<input type="hidden" name="seq">
	<input type="hidden" name="filename">
</form>

<script type="text/javascript">
function filedown(newfilename, seq, filename) {
	let doc = document.file_Down;
	doc.newfilename.value = newfilename; 
	doc.seq.value = seq;
	doc.filename.value = filename;
	doc.submit();
}
</script>
</body>
</html>