<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Calendar cal = Calendar.getInstance();
int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH) + 1;
int tday = cal.get(Calendar.DATE);
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!-- 글씨체추가 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- css 추가 -->
<link href="css/styles-sb-admin.css" rel="stylesheet" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript" ></script>
<script src="https://kit.fontawesome.com/e682320f10.js" crossorigin="anonymous"></script>

<!-- 추가 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>

<title>Please Join Us!</title>
<style type="text/css">
body{
	font-family: 'Noto Sans KR', sans-serif;
}
th{
	padding: 20px;
	text-align: center;
}
td{
	padding: 10px;
}
.logo{
	text-align: center;
}
input,select{
	padding: 10px;
}
.select {
 	position: relative;
 	width:95px;
 	font-size:20px;
}
</style>
</head>
  <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Create Account</h3></div>
                                    <div class="card-body">
										<div align="center" class="center">
											<form action="member" method="post">	
											<input type="hidden" name="param" value="regiAf">	<!--member?param=regiAf : 보내고 싶은값 -->
												<table>
													<tr>
														<th>ID</th>
														<td style="display: inline-block;">
															<input type="text" id="id" class="form-control input-lg" onblur="ischeck(this.value)" name="id" placeholder="Id입력난" size="40">
															<div id="idcheck"></div>
															<div class="msg"></div>
															<div class="idAndpw"></div>
														</td>
														<td>
															<input type="button" id="btn" class="btn btn-warning" name="btn" value="아이디확인">
														</td>
													</tr>
													<tr>
														<th rowspan="2">Password</th>
														<td>
															<input type="password" id="pwd" class="form-control input-lg" onblur="ischeck(this.value)" name="pwd" placeholder="비밀번호 입력난" size="40">
															<div class="msg"></div>
															<div class="idAndpw"></div>
														</td>
													</tr>
													<tr>
														<td>
															<input type="password" id="pwdcheck" class="form-control input-lg" onblur="ispassword(this.value)" name="pwdcheck" placeholder="비밀번호를 다시 입력해주세요." size="40">
															<div id="pwMsg"></div>
														</td>
													</tr>
													<tr>
														<th>이름</th>
														<td>
															<input type="text" id="name" class="form-control input-lg" onblur="ischeck(this.value)" name="name" size="10">
															<div class="msg"></div>
														</td>
														<td>
															<input type="radio" id="gender" name="gender" value="male">&nbsp;남 
															<input type="radio" id="gender" name="gender" value="female" checked="checked">&nbsp;여
														</td>
													</tr>
												 	<tr>
												 		<th>생년월일</th>
														<td>
															<select class="form-control" name="year">
															<%
																for(int i = tyear - 70;i <= tyear; i++){
																	%>	
																	<option <%=(tyear+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
																		<%=i %>
																	</option>
																	
																	<%
																}		
															%>		
															</select>	
															
															<select class="form-control" name="month">
															<%
																for(int i = 1;i <= 12; i++){
																	%>	
																	<option <%=(tmonth+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
																		<%=i %>
																	</option>
																	
																	<%
																}		
															%>		
															</select>
															
															<select class="form-control" name="day">
															<%
																for(int i = 1;i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
																	%>	
																	<option <%=(tday+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
																		<%=i %>
																	</option>				
																	<%
																}		
															%>		
															</select>
														</td>
													</tr> 
													<tr>
														<th>지역</th>
														<td>
															<input type="text" class="form-control input-lg" name="address" id="address" placeholder="주소">
															<input type="text" class="form-control input-lg" name="detailAddress" id="detailAddress" onchange="checkMap()" placeholder="상세주소">
															<input type="text" class="form-control input-lg" id="extraAddress" placeholder="참고항목">
															<div class="msg"></div>	
														</td>
														<td>
															<input type="button" class="btn btn-warning" onclick="Postcode()" value="주소찾기">
														</td>
													</tr>
													<tr>
														<th>휴대폰번호</th>
														<td>
															<input type="text" id="phone" class="form-control input-lg" onblur="ischeck(this.value)" name="phone" size="40" placeholder="하이픈(-)제외">
															<div class="msg"></div>
														</td>
													</tr>
													<tr>
														<th>이메일주소</th>
														<td>
															<input type="text" id="email" class="form-control input-lg" onblur="ischeck(this.value)" name="email" size="30" placeholder="이메일주소를 입력하세요">
															<div class="msg"></div>
														</td>
													</tr>
													<tr>
														<th>카테고리</th>
														<td>
															<select name="category" class="form-control">
													            <option value="sports">운동/스포츠</option>
													            <option value="entertaiment">문화/공연/축제</option>
													            <option value="readBook">책/글/인문학</option>
													            <option value="language">외국/언어</option>
													            <option value="vehicle">차/오토바이</option>
													            <option value="game">게임</option>
													            <option value="cook">요리</option>
													            <option value="pets">반려동물</option>
												         	</select>
														</td>
													</tr>	
													<tr>
														<td style="text-align: center;" colspan="2">
															<input type="submit" class="btn btn-warning" value="가입하기" style="margin-left: 100px;">
														</td>
													</tr>
												</table>
											</form>
										</div>
									</div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="member?param=login"></a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
<script type="text/javascript">
$("select[name='day']").val("<%=(tday+"") %>");
$(document).ready(function() {
	
	$("select[name='month']").change( setMonth );
	
	function setMonth() {
//		alert('setDay()');
		let year = $("select[name='year']").val();
		let month = $("select[name='month']").val();

		let lastday = (new Date(year, month, 0)).getDate();
//		alert(lastday);

		// 날짜 적용
		let str = '';
		for(i = 1;i <= lastday; i++){
			str += "<option value='" + i + "'>" + i + "</option>";
		}
		
		$("select[name='day']").html(str);
	}
	
	//아이디확인 = ajax(화면이 고정되면서 데이터 갖고 오는것)
//	setDateBox();
	$("#btn").click(function() {
		if($('#id').val().trim() == ""){
			alert("아이디 입력하세요.");
			return;
		}
		
		$.ajax({
			url:"member?param=idcheck",
			type:"post",
			data:{ "id":$("#id").val() },
			success:function( data ){
			// 	alert(data.str);
				if(data.str == "idnotfound"){
					$("#idcheck").css({"color":"blue", "font-size":"10px"});
					$("#idcheck").text("사용할 수 있는 아이디입니다");
					$("#id").val($("#id").val());
				}else{
					$("#idcheck").css({"color":"red", "font-size":"10px"});
					$("#idcheck").text("사용할 수 없는 아이디입니다");
					$("#id").val("");	//값 지워주기
				} 
			},
			error:function(){
				alert('error');
			}			
		});
	});
});
 
function ischeck(input) {//0630 항목 미기입시 경고문구 출력-> 현재 에러남
	
	if(input == null || input.trim() == ""){
		$('.msg').html("필수정보입니다").css({"color":"red", "font-size":"10px"});
		idchecked = false;
	}
	
	if(1 < input.length && input.length < 8 || input.length > 15 ){
		$('.idAndpw').html("8자이상 15이하 작성해주세요").css({"color":"red", "font-size":"10px"});
	}
}

function ispassword(input) {
	let pwd = $('#pwd').val();

	if(pwd != input){
		$('#pwMsg').html("비밀번호가 틀립니다.다시입력해주세요").css({"color":"red", "font-size":"10px"});
		$('#pwd').val("");
		$('#pwcheck').val("");
	}else if(pwd == input){
		$('#pwMsg').html("비밀번호가 같습니다.").css({"color":"blue", "font-size":"10px"});
	}
	
	if(input == ""){
		$('#pwMsg').html("비밀번호 입력해주세요").css({"color":"red", "font-size":"10px"});
	}
}

</script>



<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> <!-- 우편변호 -->
<script>
////////////////////////////우편번호 JS영역////////////////////////////////
function Postcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            //document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}
</script>
</body>
</html>