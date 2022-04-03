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
</head>
<body>
<form id="searchAjax">
	<div>
		<input type="hidden" name="pageNumber" id="pageNumber">
		<select id="choice" name="choice">
			<option value="" selected>선택</option>
			<option value="memid">작성자</option>
			<option value="boardSubject">제목</option>
			<option value="boardCntSub">제목+내용</option>
		</select>
		<input type="text" size="20" id="search" name="search" value="${list.paging.search }">
		<input type="button" value="검색" style="margin: 10px" onclick="paging()">
		<input type="button" value="검색ajax" style="margin: 10px" id="pagingAjax"><br>
		<input type="date" id="startdate" name="startdate" value="${list.paging.startdate}"> - 
		<input type="date" id="enddate" name="enddate" value="${list.paging.enddate}">
	</div>
</form>
<input type="button" value="글쓰기" style="margin: 10px" onclick="writeBoard()">
<input type="button" value="삭제" style="margin: 10px" onclick="deleteBoard()">
<div id="boardTable">
	<table border="1">
		<tr>
			<th>체크</th>
			<th>글번호</th>
			<th>작성자(ID)</th>
			<th>제목</th>
			<th>작성일</th>
			<th>수정일</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${list.list}" var = "list">
			<tr>
				<td><input type="checkbox" name="checks" value="${list.seq }"></td>
				<td>${list.seq }</td>
				<td>${list.memName }(${list.memId })</td>
				<td><a href="boardDetail?seq=${list.seq }">${list.boardSubject }</a></td>
				<td>${list.regDate }</td>
				<td>${list.uptDate }</td>
				<td>${list.viewCnt }</td>
			</tr>
		</c:forEach>
		<tr>
			<td width="100%" colspan="7">
				<c:if test="${list.paging.prev == true}">
					<a href="#" onclick="paging()">[처음]</a>
					<a href="#" onclick="paging(${list.paging.end } - 5)">[이전]</a>
				</c:if>
				<c:forEach var="cnt" begin="${list.paging.start }" end="${list.paging.end }">
					<a href="#" onclick="paging(${cnt})">${cnt}</a>
				</c:forEach>
				<c:if test="${list.paging.next == true}">
					<a href="#" onclick="paging(${list.paging.start } + 5)">[다음]</a>
					<a href="#" onclick="paging(${list.paging.totalPage })">[끝]</a>
				</c:if>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
	$(function() {				
		$("#choice").val('${list.paging.choice}').prop("selected",true);
		
		$("#pagingAjax").click(function(){
			ajax(1);
		});
	});
	
	function ajax(pageNum){
		$("#pageNumber").val(pageNum);
		$.ajax({
			url : 'listAjax',
			data : $("#searchAjax").serialize(),
			type : 'get',
			success : function(list) {
				console.log(list);
				
				// 비동기는 필요없음
				/* $("#choice").val(list.paging.choice).prop("selected",true);
				$("#search").val(list.paging.search);
				$("#startdate").val(list.paging.startdate);
				$("#enddate").val(list.paging.enddate); */
				
				var html = '<table border="1">';
				html +=		'<tr>';
				html +=				'<th>체크</th>';
				html +=				'<th>글번호</th>';
				html +=				'<th>작성자(ID)</th>';
				html +=				'<th>제목</th>';
				html +=				'<th>작성일</th>';
				html +=				'<th>수정일</th>';
				html +=				'<th>조회수</th>';
				html +=			'</tr>';
							$.each(list.list, function(index,item){
				html +=			'<tr>';
				html +=			   '<td><input type="checkbox" name="checks" value="' + item.seq +'"></td>';
				html +=			   '<td>' + item.seq + '</td>';
				html +=			   '<td>' + item.memName + '(' + item.memId + ')</td>';
				html +=			   '<td><a href="boardDetail?seq=' + item.seq + '">' + item.boardSubject + '</a></td>';
				html +=			   '<td>' + new Date(item.regDate).toLocaleString() + '</td>';
								var update = new Date(item.uptDate).toLocaleString();
								if (update != 'Invalid Date') {
				html +=			   '<td>' + new Date(item.uptDate).toLocaleString() + '</td>';
								} else {
				html +=			   '<td></td>';					
								}
				html +=			   '<td>' + item.viewCnt + '</td>';
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

				$("#boardTable").html(html);			
			},
			error : function() {
				
			}
		});
	}
	
	function writeBoard() {
		location.href = "writeboard?seq=0";
	}
	
	function deleteBoard() {
		var result = [];
		var chked = document.querySelectorAll('input[name="checks"]:checked');
//		var chked = $('[name="checks"]:checked');
 		for (i=0; i<chked.length; i++) {
			result[i] = chked[i].value;
		}
		location.href = "mutiDeleteBoard?seqs=" + result;
	}
	
	/*동기식 페이징*/
	function paging(page) {
		if (page === undefined) {
			page = 1;
		}
		
 		location.href = 'list?choice=' + $("#choice").val() + 
						    '&search=' + $("#search").val().trim() +
						    '&startdate=' + $("#startdate").val() +
						    '&enddate=' + $("#enddate").val() +
						    '&pageNumber=' + page;
	}
</script>
</body>
</html>