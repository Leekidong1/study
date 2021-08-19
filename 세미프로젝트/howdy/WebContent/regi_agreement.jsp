<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style type="text/css">
body{
	position: relative;
	height: 100%;
	font-family: 'Noto Sans KR', sans-serif;
}
</style>
</head>
<body style="text-align: center;">
<div align="center">
	<img alt="" src="image/howdy004.png" style="width:300px; height:100px; text-align: center;">
	<div style="width:300px; height:100px; margin-top: 20px;">
		<input type="checkbox" id="check1" onclick="checkAll()">
		<b>하우디 이용약관, 개인정보 수집 및 이용, 위치정보 이용약관(선택),프로모션 정보 수신(선택)에 모두 동의합니다.</b>
	</div>
	<input type="checkbox" id="check2" name="checkAll" style="margin: 10px">네이버 이용약관 동의<sub style="color:#31B404">(필수)</sub>
	<div style="overflow:auto; width:400px; height:200px; border-style:solid; border-color: #BDBDBD;">
		<h3>여러분을 환영합니다.</h3>
		<p>하우디 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 
		본 약관은 다양한 하우디 서비스의 이용과 관련하여 하우디 서비스를 제공하는 하우디 주식회사(이하 ‘하우디’)와 
		이를 이용하는 하우디 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 
		아울러 여러분의 하우디 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.</p>
	</div>
	<input type="checkbox" id="check3" name="checkAll" style="margin: 10px">개인정보 수집 및 이용 동의<sub style="color:#31B404">(필수)</sub>
	<div style="overflow:auto; width:400px; height:200px; border-style:solid; border-color: #BDBDBD;">
		<p>개인정보보호법에 따라 하우디에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 
		개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.</p>
		<p>1. 수집하는 개인정보</p>
		<p>이용자는 회원가입을 하지 않아도 정보 검색, 뉴스 보기 등 대부분의 하우디 서비스를 회원과 동일하게 이용할 수 있습니다. 
		이용자가 메일, 캘린더, 카페, 블로그 등과 같이 개인화 혹은 회원제 서비스를 이용하기 위해 회원가입을 할 경우, 하우디는 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.</p>
	</div>
	<input type="checkbox" id="check4" name="checkAll" style="margin: 10px">위치정보 이용약관 동의<sub style="color:#585858">(선택)</sub>
	<div style="overflow:auto ; width:400px; height:200px; border-style:solid; border-color: #BDBDBD;">
		<p>위치정보 이용약관에 동의하시면, <b>위치를 활용한 광고 정보 수신</b>등을 포함하는 네이버 위치기반 서비스를 이용할 수 있습니다.</p>
		<p>제 1 조 (목적)</p>
		<p>이 약관은 네이버 주식회사 (이하 “회사”)가 제공하는 위치정보사업 또는 위치기반서비스사업과 관련하여 회사와 개인위치정보주체와의 권리, 
		의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.</p>
	</div><br>
	<p id="warning" style="color: red"></p>
	<br>
	<div>
		<a href="login.jsp" >
			<button class="btn btn-secondary" >취소</button>
		</a>
		<a href="regi.jsp" id="movenext" >
			<button onclick="agreement()" class="btn btn-warning">확인</button>
		</a>
	</div>
</div>
<!-- Java Script -->
<script type="text/javascript">
	function checkAll() {
		if(document.getElementById("check1").checked == true){
			for (i = 0; i < 3; i++) {
				document.getElementsByName("checkAll")[i].checked=true; //**name="checkAll"의 배열형태로 true를 적용
			}
		}else{
			for (i = 0; i < 3; i++) {
				document.getElementsByName("checkAll")[i].checked=false;
			}
		}
	}
	
	function agreement() {
		
		let agrees = new Array(3);
		agrees[0] = document.getElementById("check1").checked;		
		agrees[1] = document.getElementById("check2").checked;	
		agrees[2] = document.getElementById("check3").checked;	
		
		if(agrees[1] == true && agrees[2] == true){
			document.getElementById("movenext").href = "regi.jsp";
			return;	
		}else{
			document.getElementById("warning").innerHTML = "'네이버 이용약관'과 '개인정보 수집 및 이용'에 대한 안내 모두 동의해주세요.";
			document.getElementById("movenext").href = "#none";
		}	
		
	}
</script>
</body>
</html>