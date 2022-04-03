<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function downloadFile(img) {
		console.log($(img).text());
//		location.href = "downloadFile?filename=" + $(img).text();
	}
</script>
</head>
<body>
	<h1>글자세히보기</h1>
	<table border="1">
		<colgroup>
			<col style="width:200px" />
			<col style="width:auto" />
		</colgroup>
		<tr>
			<th>아이디</th>
			<td>
				<input type="text" name="memId" id="id" size="20" readonly="readonly" value="${detail.memId }">
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<input type="text" name="memName" id="name" size="20" readonly="readonly" value="${detail.memName }">
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td style="text-align: left">
				<input type="text" name="boardSubject" size="30" readonly="readonly" value="${detail.boardSubject }">
			</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td style="text-align: left">
				${detail.regDate }
			</td>
		</tr>
		<tr>
			<th>수정일</th>
			<td style="text-align: left">
				${detail.uptDate }
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td style="text-align: left">
				${detail.viewCnt }
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td style="text-align: left">
				<textarea rows="20px" cols="80px" name="boardContent">${detail.boardContent }</textarea>
			</td>
		</tr>
		<c:if test="${not empty files}">
		<tr>
			<th>파일</th>
			<td style="text-align: left">
				<c:forEach items="${files}" var = "file">
					<div><a href="#" onclick="downloadFile(this)">${file.realName}</a></div>
				</c:forEach>
			</td>
		</tr>
		</c:if>
	</table>
	<input type="button" value="목록" onclick="location.href='list'">
	<input type="button" value="삭제" onclick="location.href='deleteBoard?seq=${detail.seq}'">
	<input type="button" value="수정" onclick="location.href='writeboard?seq=${detail.seq}'">
</body>
</html>