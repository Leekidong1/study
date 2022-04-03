<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
	ajax(1);
	$("#logout").on('click', function(){
		location.href = "logout";
	});
	
	$("#paging").click(function(){
		ajax(1);
	});
	
	$("#approval").on('change', function name() {
		ajax(1);
	});
});

function ajax(pageNum) {
	$("#pageNumber").val(pageNum);
	$.ajax({
		url : 'list',
		data : $("#searchAjax").serialize(),
		type : 'get',
		success : function(list) {
			console.log(list);
			
			var html = '<table border="1">';
			html +=			'<tr>';
			html +=				'<th>번호</th>';
			html +=				'<th>작성자</th>';
			html +=				'<th>제목</th>';
			html +=				'<th>작성일</th>';
			html +=				'<th>결재일</th>';
			html +=				'<th>결재자</th>';
			html +=				'<th>결재상태</th>';
			html +=			'</tr>';
						$.each(list.list, function(index,item){
			html +=			'<tr>';
			html +=			   '<td>' + item.idx + '</td>';
			html +=			   '<td>' + item.writeId + '</td>';
			html +=			   '<td><a href="approval?seq=' + item.seq + 
													'&status=' + item.apprStatus + 
												    '&writer=' + item.writeId + 
												    '&subject=' + item.apprSubject + 
												    '&content=' + item.apprContent + '">' + item.apprSubject + '</a></td>';
			html +=			   '<td>' + new Date(item.apprRegdate).toLocaleString() + '</td>';
							var apprdate = new Date(item.apprDate).toLocaleString();
							if (apprdate != 'Invalid Date') {
			html +=			   '<td>' + new Date(item.apprDate).toLocaleString() + '</td>';
							} else {
			html +=			   '<td></td>';					
							}
							if (item.apprApper != null) {
			html +=			   '<td>' + item.apprApper + '</td>';
							} else {
			html +=			   '<td></td>';					
							}
			html +=			   '<td>' + item.apprStatus + '</td>';
			html +=			'</tr>';
						});
			html +=		'<tr>';
			html +=			'<td width="100%" colspan="7">';
								if (list.paging.prev == true){
			html +=					'<a href="#" onclick="ajax(1)">[처음]</a>&nbsp;';
			html +=					'<a href="#" onclick="ajax(' + list.paging.end + ' - 5)">[이전]</a>&nbsp;';
								}
								for (i=list.paging.start; i<=list.paging.end; i++) {
			html +=					'<a href="#" onclick="ajax(' + i + ')">' + i + '</a>&nbsp;';
								}
								if (list.paging.next == true){
			html +=					'<a href="#" onclick="ajax(' + list.paging.start + ' + 5)">[다음]</a>&nbsp;';
			html +=					'<a href="#" onclick="ajax(' + list.paging.totalPage + ')">[끝]</a>&nbsp;';
								}
			html +=			'</td>';
			html +=		'</tr>';
			html +=	'</table>';

			$("#apprTabel").html(html);
		},
		error : function() {
			
		}
	});
}
</script>
</head>
<body>
<span style="margin: 20 20 20 20">
	${login.memName}(
					<c:choose>
						<c:when test="${login.memRank == 'sa'}">
							사원
						</c:when>
						<c:when test="${login.memRank == 'da'}">
							대리
						</c:when>
						<c:when test="${login.memRank == 'ga'}">
							과장
						</c:when>
						<c:when test="${login.memRank == 'ba'}">
							부장
						</c:when>
					</c:choose>
					) 님 환영합니다.
</span>
&nbsp;&nbsp;<input type="button" value="로그아웃" id="logout"><br>
<input type="button" value="글쓰기" onclick="location.href='approval?seq=0&status=&writer=${login.memId}'">
<c:if test="${login.memRank == 'ga' || login.memRank == 'ba'}">
	<input type="button" value="대리결재">
</c:if>
<form id="searchAjax">
	<div>
		<input type="hidden" name="memId" value="${login.memId}">
		<input type="hidden" name="memRank" value="${login.memRank}">
		<input type="hidden" name="pageNumber" id="pageNumber">
		<select id="choice" name="choice">
			<option value="" selected>선택</option>
			<option value="memId">작성자</option>
			<option value="approvaler">결재자</option>
			<option value="boardCntSub">제목+내용</option>
		</select>
		<input type="text" size="20" id="search" name="search">
		<select id="approval" name="approval">
			<option value="" selected>결재상태</option>
			<option value="tmp">임시저장</option>
			<option value="wat">결재대기</option>
			<option value="ing">결재중</option>
			<option value="end">결재완료</option>
			<option value="ret">반려</option>
		</select><br>
		<input type="date" id="startdate" name="startdate"> - 
		<input type="date" id="enddate" name="enddate">
		<input type="button" value="검색" style="margin: 10px" id="paging">
	</div>
</form>
<div id="apprTabel"></div>
</body>
</html>