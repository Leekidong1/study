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
<script>
	$(function(){
		var seq = '${seq}';
		$("#regBtn").click(function(){
			var address = 'writeBoardAf';
			
			/*validation*/
			if ($('#id').val().trim() == '') {
				alert("id입력해주세요");
				return
			}
			if ($('#name').val().trim() == '') {
				alert("이름입력해주세요");
				return
			}
			if ($('#subject').val().trim() == '') {
				alert("제목입력해주세요");
				return
			}
				
			
			/*수정하기*/
			if (seq != 0) {
				address = 'updateBoardAf';
			}
			
			/* 동기방식으로 http통신*/
			$("#writeFrm").attr({"action":address, "method":"post"}).submit();
			
			/* 비동기 ajax이용한 http통신*/
/* 			console.log($("#writeFrm").serialize());
			$.ajax({
				url : address,
				data : $("#writeFrm").serialize(),
				type : 'post',
				success : function(response) {
					alert(response);
					location.href = "list";
				},
				error : function(e) {
					
				}
			});	 */
		})
		
		var idx = 1;
		$('#btnFileUpload').click(function(){
			var newBox = 	'<div style="margin-top: 5px" id="box' + idx + '">';
				newBox +=		'<input type="file" name="fileload" onchange="fileCheck(this)">';
				newBox +=		'<input type="button" onclick="deleteBox(' + idx + ')" value="삭제">';
				newBox +=	'</div>';
			$("#uploadBox").append(newBox);
			idx += 1;
		})
		
	})
	
	function deleteBox(idx) {
		$("#box"+idx).remove();
	}
	
	/*file upload validation*/
	function fileCheck(file) {
		/*확장자명 확인*/
		var post = file.files[0].name.split('.').pop().toLowerCase();
		
		if ($.inArray(post, ['gif','jpg','jpeg','png','bmp']) == -1) {
			alert("gif, jpg, jpeg, png, bmp 파일만 업로드 해주세요.");
			$(file).val('');
			return
		}
		
		var _URL = window.URL || window.webkitURL;
		var img = new Image();
		
		img.src = _URL.createObjectURL(file.files[0]);
		img.onload = function() {
			if (img.width > 500 || img.height > 500) {
				alert("500*500 픽셀 이하만 업로드해주세요.");
				$(file).val("");
			}
		}
		
		// 이미지 - 주소값을 가져온다
		console.log($("input[name=fileload]"));
		file.files[0]
	}
</script>
</head>
<body>
<c:choose>
	<c:when test="${seq != 0}">
		<h1>글수정</h1>
	</c:when>
	<c:otherwise>
		<h1>새로운 글등록</h1>
	</c:otherwise>
</c:choose>
<form name = "writeFrm" id = "writeFrm" enctype="multipart/form-data">
	<table border="1">
		<colgroup>
			<col style="width:200px" />
			<col style="width:auto" />
		</colgroup>
		<tr>
			<th>아이디</th>
			<td>
				<input type="text" name="memId" id="id" size="20" value="${board.memId}">
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<input type="text" name="memName" id="name" size="20" value="${board.memName}">
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td style="text-align: left">
				<input type="text" name="boardSubject" id="subject" size="30" value="${board.boardSubject}">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td style="text-align: left">
				<textarea rows="20px" cols="80px" name="boardContent">${board.boardContent}</textarea>
			</td>
		</tr>
		<tr>
			<th>다운로드</th>
			<td style="text-align: left" id="uploadBox">
				<input type="button" id="btnFileUpload" value="파일추가">
			</td>
		</tr>
	</table>
	<c:choose>
		<c:when test="${seq != 0}">
			<input type="button"  name = "regBtn" id ="regBtn" value="수정">
			<input type="hidden" name="seq" value="${board.seq }">
		</c:when>
		<c:otherwise>
			<input type="button"  name = "regBtn" id ="regBtn" value="등록">
		</c:otherwise>
	</c:choose>
	<input type="button" value="목록" onclick="location.href='list'">
</form>
</body>
</html>