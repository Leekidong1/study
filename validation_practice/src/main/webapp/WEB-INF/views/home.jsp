<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<form>
	<table width="1400" height="650">
		<tr>
			<td width="100%" height="10%">회원가입</td>
		</tr>
  		<tr>
   			<td height="60%" align="center" valign="top">
	   			<hr><br>
	   			<p align="left" style="padding-left:160px">
		   			<br><br>
		   			ID : <input type="text" size="10" maxlength="15" name="id" id = "id">
		   			<input type = "button" name = "idChk" value = "중복체크" onclick="fncIdcheck()" >
		   			<br><br>
		  			비밀번호 : <input type="password" size="15" maxlength="20" name="pass" id = "pass"><br><br>
		  			비밀번호 확인 : <input type="password" size="15" maxlength="20" name="pass2" id = "pass2"><br><br>
		   			이름 : <input type="text" size="13" name="name" id = "name" onkeyup="fncNameKeyUp(this)"><br><br>
		   			이메일 : <input type="text" size="15" name="email1" id = "email1">@<input type="text" size="15" name="email2" id = "email2"><br><br>
		   			휴대폰 : <select name="ph1">
		       					<option value="010">010</option>
		       					<option value="011">011</option>
		       					<option value="016">016</option>
		       					<option value="017">017</option>
		       					<option value="019">019</option>
		     			   </select>
		     		- <input type="text" name="ph2" size="5" maxlength="4" id = "ph2" onkeyup="fncPhKeyUp(this)"> - <input type="text" name="ph3" size="5" maxlength="4" id = "ph3"><br><br>
		   			성별 : <input type="radio" name="gender" value="남자" checked="checked"> 남자&nbsp;&nbsp;
		   				 <input type="radio" name="gender" value="여자"> 여자<br><br>
		   			주민번호 : <input type = "text" name = "jumin1" id = "jumin1"> -  <input type = "password" name = "jumin2" id = "jumin2" onkeyup="fncJuminKeyUp(this)"><br><br>
		   			주소 : <input type="text" name="address" size="15" maxlength="15" id="address"><br>
		   			*주소는 (시/도)만 입력해주세요 (예: 경기도, 서울특별시, 경상남도 등)
	   			</p>
  			</td>
  		</tr>
  		<tr>
   			<td align="center">
   				<hr><br>
			    <input type="button"  id = "regi_btn" value="가입신청">&nbsp;
			    <input type="reset" value="다시입력">&nbsp;
			    <input type="button" value="취소">
	  		</td>
  		</tr>
 	</table>

 	<div>
	 	<hr width="1400" align="left"><br>
	 	<input type="checkbox" onclick="fncChkAllBtn(this)" id="chkAll" value="전체">전체
	 	<input type="button" onclick="fncBtn(this)" value="버튼"><br>
	 	<input type="checkbox" name="cities" id="city1" onclick="fncBtn(this)" value="서울"><label for="city1">서울</label>
	 	<input type="checkbox" name="cities" id="city2" onclick="fncBtn(this)" value="인천"><label for="city2">인천</label>
	 	<input type="checkbox" name="cities" id="city3" onclick="fncBtn(this)" value="경기"><label for="city3">경기</label>
	 	<input type="checkbox" name="cities" id="city4" onclick="fncBtn(this)" value="강원"><label for="city4">강원</label>
	 	<input type="checkbox" name="cities" id="city5" onclick="fncBtn(this)" value="부산"><label for="city5">부산</label>
	 	<input type="checkbox" name="cities" id="city6" onclick="fncBtn(this)" value="대전"><label for="city6">대전</label>
	 	<input type="checkbox" name="cities" id="city7" onclick="fncBtn(this)" value="전남"><label for="city7">전남</label>
	 	<input type="checkbox" name="cities" id="city8" onclick="fncBtn(this)" value="제주"><label for="city8">제주</label>
	 	<input type="checkbox" name="cities" id="city9" onclick="fncBtn(this)" value="평양"><label for="city9">평양</label>
	 </div>
 	<div id="msg"></div>
</form>

<script type="text/javascript">
/*정규식을 이용한 유효성검사*/
var noEngNum = /^[a-zA-Z0-9]*$/;					/*영문,숫자*/
var noEngKor = /^[ㄱ-ㅎ|가-힣|a-z|A-Z]+$/; 			/*영문,한글*/
var noNum = /^[0-9]*$/;								/*숫자*/
var noEngKorNum = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9]+$/; 	/*영문,숫자,한글*/ 
	
// 	.test .match
	
	function fncIdcheck() {
		var str = $('#id').val().trim();
		
 		if (str == ''){
 			fncMessage("ID 빈값", "id");
 			return
		} else if (!noEngNum.test(str)) {
			fncMessage("ID는 영문과 숫자만 입력해주세요.", "id");
			return
 	    } else if (str === "test") {
 	    	fncMessage("ID중복", "id");
 	    	return
 		} else {
 			fncMessage("사용가능한 아이디", "id");
 			$('#id').prop("disabled", true);
 		}
	}
	
	/*정규식 유효성체크 및 글자 5개 이하 입력*/
	function fncNameKeyUp(input) {
		
// 		$(input).val()
		var name = input.value;
		
		if (!noEngKor.test(input.value)) {
			fncMessage("한글 또는 영어만 입력해주세요.", "name");
			return
		} else if (name.length > 5) {
			fncMessage("5개 글자이하로 작성해주세요.", "name");
			return
		}
	}
	
	/*메세지 팝업창*/
	function fncMessage(msg, htmlId){
		alert(msg);
		$("#" + htmlId).val("").focus();
	}
	
	/*중간 전화번호 digits 4개입력 -> 3번째로 커서이동*/
	function fncPhKeyUp(input) {
		if (input.value.length == 4){
			$("#ph3").focus();
		}
	}
	
	/*주민번호 뒷자리 맨첫자리만 숫자 1*******/
	function fncJuminKeyUp(input) {
		var jumin = input.value;
		var digits = jumin.substring(0,1);
		
// 		.slice(2, 4), .substring(2, 4)
		
		
		if(jumin.length > 1){
			for (i=0; i<jumin.length-1; i++){
				digits += '*';
			}
		}
		$("#jumin2").attr('type',"text").val(digits);
	}
	
	$("#regi_btn").click(function name() {
		
		/*id validation*/
		if ($('#id').val().trim() == '') {
			fncMessage("ID 빈값입니다.", "id");
 			return
		}
		
		/*name validation*/
		if ($("#name").val().trim() == ''){
			fncMessage("이름 빈값입니다.", "name");
			return
		}
		
		/*password validation*/
		if ($('#pass').val().trim() != $('#pass2').val().trim()) {
			alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
			$('#pass2').val("");
 	    	$("#pass").val("").focus();
 	    	return
		}
		
		/*email validation*/
		if ($("#email1").val().trim() == "" || $("#email2").val().trim() == "") {
			fncMessage("email 빈값입니다.", "email1");
			return
		} else if (!noEngNum.test($('#email1').val())) {
			fncMessage("email는 영문과 숫자만 입력해주세요.", "email1");
			return
		} else if (!$('#email2').val().includes('.')) {
			fncMessage("email 형식에 맞게 작성해주세요.", "email2");
			return
		}
		
		/*phone validation*/
		var ph2 = $("#ph2").val().trim();
		var ph3 = $("#ph3").val().trim();
		if (ph2 == "" || ph3 == "") {
			fncMessage("전화번호 입력해주세요.", "ph2");
			return
		} else if (!noNum.test(ph2) || !noNum.test(ph3)) {
			alert("전화번호 숫자만 입력해주세요.");
			$('#ph3').val("");
 	    	$("#ph2").val("").focus();
			return
		}
		
		/*identity number validation*/
		var jumin1 = $("#jumin1").val().trim();
		var jumin2 = $("#jumin2").val().trim();
		if (jumin1 == "" || jumin2 == "") {
			fncMessage("주민번호 입력해주세요.", "jumin1");
			return
		} else if (!noNum.test(jumin1) || !noNum.test(jumin2)) {
			alert("주민번호 숫자만 입력해주세요.");
			$('#jumin2').val("");
 	    	$("#jumin1").val("").focus();
			return
		}
		
		/*address validation*/
		if ($("#address").val().trim() == "") {
			fncMessage("주소 입력해주세요.", "address");
			return
		} else if (!noEngKorNum.test($("#address").val().trim())) {
			fncMessage("주소는 한글,영문,숫자만 입력해주세요.", "address");
			return
		}
	});
	var text = [];
	/*checkbox 전체 체크 및 해제 함수*/
	function fncChkAllBtn(input) {
		var chkboxes = document.querySelectorAll('input[name="cities"]');
		var chked = document.querySelectorAll('input[name="cities"]:checked');
		if (input.checked) {
			if(chked.length === 0) text = [];
			for (i=0; i<chkboxes.length; i++) {
				if (chked.length > 0) {
					if (text.indexOf(chkboxes[i].value) == -1) {
						chkboxes[i].checked = true;
						text.push(chkboxes[i].value);
					}
				} else {
					chkboxes[i].checked = true;
					text.push(chkboxes[i].value);
				}
			}
		} else {
			for (i=0; i<chkboxes.length; i++){
				chkboxes[i].checked = false;
				text = [];
			}
		}
		msgCities();
	}
	
	/*checkbox 버튼 및 도시별 checkbox 함수*/
	function fncBtn(input) {
		var chkboxes = document.querySelectorAll('input[name="cities"]');
		var chked = document.querySelectorAll('input[name="cities"]:checked');	
		
		//도시별 전체체크 => 전체체크 or 해제
		if (chkboxes.length === chked.length) {
			document.getElementById("chkAll").checked = true;
		} else {
			document.getElementById("chkAll").checked = false;
		}
		//항목 4개이하, 전체체크 시 => 성공 ... 반대는 실패
		if (input.value === '버튼') {
			if (chked.length < 5 || chkboxes.length === chked.length) {
				alert('성공');
				return
			} else {
				alert('실패');
				return
			}
		}
		//체크할시 텍스트 생김 => 해제시 글삭제
		if (input.checked) {
			text.push(input.value);
		} else {
			var idx = text.indexOf(input.value);
			text.splice(idx,1);
		}
		msgCities();
	}
	
	function msgCities() {
		var result='';
		for (i=0; i<text.length; i++) {
			result += text[i] + ", ";
		}
		$("#msg").text(result);
	}
	
	function isValidJuminNo() {
	    var jumin1 = $("#jumin1").val();
	    var jumin2 = $("#jumin3").val();
	    var yy     = jumin1.substr(0,2);        // 년도
	    var mm     = jumin1.substr(2,2);        // 월
	    var dd     = jumin1.substr(4,2);        // 일
	    var genda  = jumin2.substr(0,1);        // 성별
	    var msg, ss, cc;
	    
	      // 숫자가 아닌 것을 입력한 경우
	    if (!isNumeric(jumin1)) {
	    	alert("주민등록번호 앞자리를 숫자로 입력하세요.");
	    	$("#jumin1").focus();
	    	return false;
	    }
	     
	      // 길이가 6이 아닌 경우
	    if (jumin1.length != 6) {
	    	alert("주민등록번호 앞자리를 다시 입력하세요.");
	    	$("#jumin1").focus();
	    	return false;
	    }
	     
	      // 첫번째 자료에서 연월일(YYMMDD) 형식 중 기본 구성 검사
	    if (yy < "00" || yy > "99" || 
	    	mm < "01" || mm > "12" || 
	    	dd < "01" || dd > "31") {
	    	alert("주민등록번호 앞자리를 다시 입력하세요.");
	    	$("#jumin1").focus();
	    	return false;
	    }
	     
	      // 숫자가 아닌 것을 입력한 경우
	    if (!isNumeric(jumin2)) {
	    	alert("주민등록번호 뒷자리를 숫자로 입력하세요.");
	    	$("#jumin3").focus();
	    	return false;
	    }
	      // 길이가 7이 아닌 경우
	    if (jumin2.length != 7) {
	    	alert("주민등록번호 뒷자리를 다시 입력하세요.");
	    	$("#jumin3").focus();
	    	return false;
	    }
	     
	      // 성별부분이 1 ~ 4 가 아닌 경우
	    if (genda < "1" || genda > "4") {
	    	alert("주민등록번호 뒷자리를 다시 입력하세요.");
	    	$("#jumin3").focus();
	    	return false;
	    }
	     
	      // 연도 계산 - 1 또는 2: 1900년대, 3 또는 4: 2000년대
	    cc = (genda == "1" || genda == "2") ? "19" : "20";
	      // 첫번째 자료에서 연월일(YYMMDD) 형식 중 날짜 형식 검사
	    if (isValidDate(cc+yy+mm+dd) == false) {
	    	alert("주민등록번호 앞자리를 다시1 입력하세요.");
	    	$("#jumin1").focus();
	    	return false;
	    }
	     
	      // Check Digit 검사
	    if (!isSSN(jumin1, jumin2)) {
	    	alert("입력한 주민등록번호를 검토한 후, 다시 입력하세요.");
	    	$("#jumin1").focus();
	    	return false;
	    }
	      return true;
	}
	
	function isValidDate(iDate) {
		if( iDate.length != 8 ) {
	    	return false;
	    }
	      
	    oDate = new Date();
	    oDate.setFullYear(iDate.substring(0, 4));
	    oDate.setMonth(Number(iDate.substring(4, 6)) - 1);
	    oDate.setDate(iDate.substring(6));
	    if( Number(oDate.getFullYear())  != Number(iDate.substring(0, 4))
	       || Number(oDate.getMonth() + 1) != Number(iDate.substring(4, 6))
	       || Number(oDate.getDate())  != Number(iDate.substring(6)) ){
	    	return false;
	    }
	       
		return true;
	}
	     
	function isNumeric(s) {
		for (i=0; i<s.length; i++) {
	    	c = s.substr(i, 1);
	    	if (c < "0" || c > "9") return false;
	   	}
		return true;
	}
	     
	function isSSN(s1, s2) {
		n = 2;
	    sum = 0;
	    for (i=0; i<s1.length; i++)
	    	sum += Number(s1.substr(i, 1)) * n++;
	    for (i=0; i<s2.length-1; i++) {
	    	sum += Number(s2.substr(i, 1)) * n++;
	   		if (n == 10) n = 2;
	    }
	     
	    c = 11 - sum % 11;
	    if (c == 11) c = 1;
	    if (c == 10) c = 0;
	    if (c != Number(s2.substr(6, 1))) return false;
	    	else return true;
	    }
</script>
</body>
</html>