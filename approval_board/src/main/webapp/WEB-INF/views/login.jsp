<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
	if ('${msg}' != '') {
		alert('${msg}');
	}
	
	$("#btnLogin").on('click', function(){
		if ($("#id").val() == '') {
			alert('아이디 빈값입니다.');
			return
		}
		if ($("#pwd").val() == '') {
			alert('비밀번호 빈값입니다.');
			return
		}
		
		$('#loginForm').submit();
	});
});
</script>
</head>
<body>
<form action="loginAf" method="post" id="loginForm">
<table border="1" style="margin: auto; margin-top: 300px;">
	<tr>
		<td colspan="2" style="text-align: center;">Login</td>
	</tr>
	<tr>
		<th>아이디</th>
		<td><input type="text" id="id" name="memId" size="20"></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="password" id="pwd" name="memPwd" size="20"></td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
			<input type="button" value="로그인" id="btnLogin">
		</td>
	</tr>
</table>
</form>
</body>
</html>