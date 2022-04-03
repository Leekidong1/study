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
$(function name() {
	var status = '${apprInfo.status}';
	$("#prevStatus").val(status);
	if (status == '' || status == '임시저장' || status == '반려') {
		$("#btnRefused").hide();
		/*반려하고 작성자만 임시저장, 결재가능*/
		if (status == '반려' && '${login.memId}' != '${apprInfo.writer}') {
			$("#btnTmpSave").hide();
			$("#btnAppr").hide();
		}
	} else if (status == '결재대기') {
		$("#wat").prop("checked", true);
		$("#subject").prop("readonly", true);
		$("#content").prop("readonly", true);
		$("#btnTmpSave").hide();
		/*작성자 결재 -> 상세보기 -> 목록만 보임*/
		if ('${login.memRank}' == 'sa') {
			$("#btnRefused").hide();
			$("#btnAppr").hide();
		}
	} else if (status == '결재중') {
		$("#wat").prop("checked", true);
		$("#ing").prop("checked", true);
		$("#subject").prop("readonly", true);
		$("#content").prop("readonly", true);
		$("#btnTmpSave").hide();
		
		/*과장 결재 -> 상세보기 -> 목록만보임*/	
		if ('${login.memRank}' == 'ga') {
			$("#btnRefused").hide();
			$("#btnAppr").hide();
		}
	} else if (status == '결재완료') {
		$("#wat").prop("checked", true);
		$("#ing").prop("checked", true);
		$("#end").prop("checked", true);
		$("#subject").prop("readonly", true);
		$("#content").prop("readonly", true);
		$("#btnTmpSave").hide();
		$("#btnRefused").hide();
		$("#btnAppr").hide();
	}
	
	$("#btnRefused").on('click', function() {
		$("#nextStatus").val('ret');
		$("#apprForm").submit();
	});
	
	$("#btnTmpSave").on('click', function() {
		$("#nextStatus").val('tmp');
		$("#apprForm").submit();
	});
	
	$("#btnAppr").on('click', function() {
		if (status == '' || status == '임시저장' || status == '반려') {
			if ('${login.memRank}' == 'ga') {
				$("#nextStatus").val('ing');
			} else if ('${login.memRank}' == 'ba') {
				$("#nextStatus").val('end');
			} else {
				$("#nextStatus").val('wat');	
			}
		} else if (status == '결재대기') {
			$("#nextStatus").val('ing');
		} else if (status == '결재중') {
			$("#nextStatus").val('end');
		}
		$("#apprForm").submit();
	});
});
</script>
</head>
<body>
<table border="1">
	<col width="100"><col width="100"><col width="100">
	<tr>
		<th>결재요청</th>
		<th>과장</th>
		<th>부장</th>
	</tr>
	<tr style="text-align: center;">
		<td><input type="checkbox" id="wat" disabled></td>
		<td><input type="checkbox" id="ing" disabled></td>
		<td><input type="checkbox" id="end" disabled></td>
	</tr>
</table>
<form action="submitAppr" id="apprForm" method="post">
	<input type="hidden" id="nextStatus" name="nextStatus">
	<input type="hidden" id="prevStatus" name="prevStatus">
	<input type="hidden" name="apprer" value="${login.memId}">
	<table>
		<tr>
			<th>글번호</th>
			<td><input type="text" id="apprSeq" name="apprSeq" value="${apprInfo.seq}" readonly></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" id="writer" name="writer" value="${apprInfo.writer}" readonly></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" id="subject" name="subject" value="${apprInfo.subject}" placeholder="제목을 입력하세요"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="5" cols="25" placeholder="내용을 입력하세요" id="content" name="content">${apprInfo.content}</textarea></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;">
				<input type="button" id="btnRefused" value="반려">
				<input type="button" id="btnTmpSave" value="임시저장">
				<input type="button" id="btnAppr" value="결재">
				<input type="button" value="목록" onclick="location.href='apprList'">
			</td>
		</tr>
	</table>
</form><br>
<table border="1">
	<tr>
		<th>번호</th>
		<th>결재일</th>
		<th>결재자</th>
		<th>결재상태</th>
	</tr>
	<c:forEach items="${list}" var = "item">
	<tr>
		<td>${item.seq}</td>
		<td>${item.histApprDate}</td>
		<td>${item.histAppr}</td>
		<td>${item.histStatus}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>