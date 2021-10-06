<%@page import="dto.MemberDto"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
MemberDto mem = (MemberDto)session.getAttribute("login");
System.out.println(mem.getLocation());
if(mem == null){
%>  
	<script>
	alert("로그인 해 주십시오");
	location.href = "login.jsp";
	</script>	
<%
}
%>

<%!
// Date => String로 바꿔주는 함수 : 202106211611 -> 2021년 6월 21일 16시 11분
public String toDates(String mdate){

	//202106211611 => 2021-06-21 16:11
	String s = mdate.substring(0, 4) + "년" // yyyy
			 + mdate.substring(4, 6) + "월" // mm
			 + mdate.substring(6, 8) + "일" // dd
			 + mdate.substring(8, 10) + "시" // hh
			 + mdate.substring(10, 12) + "분"; // mm:ss
	return s;
}
%>

<%
int date = Integer.parseInt(mem.getBirth());
Calendar cal = Calendar.getInstance();
cal.set(date/10000, date/100%10-1, date%100);
int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH) + 1;
int tday = cal.get(Calendar.DATE);
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- css 추가-->
<link href="css/styles-simple-sidebar.css" rel="stylesheet" />
<!-- 글씨체추가 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- main 부트스트랩(navbar) -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
<title>MyPage_info</title>
<style type="text/css">
.logo{
 	display: inline-block;
 	padding-right: 500px; 
}
.main{
	margin-top: 80px;
}
body{
	font-family: 'Noto Sans KR', sans-serif;
}
*{
	padding: 0;
	margin: 0;
	text-decoration: none;
	list-style: none;
}
nav{
	height: 80px;
	width: 100%;
	background: #343a40;
}
label.logo{
	font-size: 35px;
	font-weight: bold;
	color: white;
	padding: 0 100px;
	line-height: 80px;
}
nav ul{
	float: right;
	margin-right: 40px;
}
nav li{
	display: inline-block;
	margin: 0 8px;
	line-height: 80px;
}
nav a{
	color: white;
	font-size: 18px;
	text-transform: uppercase;
	border: 1px solid transparent;
	padding: 7px 10px;
	border-radius: 3px;
}
a.active,a:hover{
	border: 1px solid white;
	transition: .5s
}
nav #icon{
	color: white;
	font-size: 30px;
	line-height: 80px;
	float: right;
	margin-right: 40px;
	margin-top: 20px;
	cursor: pointer;
	display: none;
}
@media (max-width: 1048px) {
	label.logo{
		font-size: 32px;
		padding-left: 60px;
	}
	nav ul{
		margin-right: 20px;
	}
	nav a{
		font-size: 17px;
	}
}
@media (max-width:909px) {
	nav #icon {
		display: block;
	}
	nav ul{
		position: fixed;
		width: 100%;
		heigth: 100vh;
		background: #2f3640;
		top: 80px;
		left: -100%;
		text-align: center;
		trnasition: all .5s;
	}
	nav li {
		display: block;
		margin: 50px 0;
		line-height: 30px;
	}
	nav a{
		font-size: 20px;
	}
	a.active,a:hover{
		border: none;
		color: #3498db;
	}
	nav ul.show{
		left: 0;
	}
	}
section{
	background-size: cover;
	height: calc(100vh - 80px);
}
</style>
</head>
<body>
<nav>
	<label class="logo"><img src="image/howdy003.png"></label>
		<ul>
			<li><a href="main.jsp">Home</a><li>
			<li><a href="main_createGroup.jsp">모임만들기</a><li>
			<li><a href="main_findGroup_main.jsp">모임찾기</a><li>
		<%
			if(mem != null){
		%>
			<li><a href="mypage_info.jsp">MyPage</a><li>
			<li><a href="login.jsp">로그아웃</a><li>
		<%
			if(mem.getAuth() == 3){
		%>	
			<li><a href="admin_Member.jsp" class="text-light">관리자페이지</a><li>
		<%
			}
			}else{
		%>	
			<li><a href="login.jsp" class="text-light">로그인</a><li>
		<%
			} 
		%>	
		</ul>
		<label id="icon">
			<i class="fas fa-bars"></i>
		</label>
</nav>
<!-- Sidebar -->
<div class="d-flex" id="wrapper">
	<div class="border-end bg-white" id="sidebar-wrapper">
		<div class="sidebar-heading border-bottom bg-light">Menu</div>
		<div class="list-group list-group-flush">
			  <a class="list-group-item list-group-item-action list-group-item-light p-3" href="mypage_info.jsp" class="w3-bar-item w3-button">개인정보관리</a>
			  <a class="list-group-item list-group-item-action list-group-item-light p-3" href="mypage_mylist.jsp" class="w3-bar-item w3-button">내 모임 리스트</a>
			  <a class="list-group-item list-group-item-action list-group-item-light p-3" href="mypage_calendar.jsp" class="w3-bar-item w3-button">내 일정</a>
			  <% 
			  	if(mem.getGroup_auth() == 3){
			  %>
			  <a class="list-group-item list-group-item-action list-group-item-light p-3" href="mypage_manageGroup.jsp" >운영중인 모임</a>
			  <%
			  	}
			  %>
		</div>
	</div>
	<!-- Page Content -->
 	<div class="container-fluid">
		<h1 class="mt-4">개인정보관리</h1>
		<hr>
		<div class="card-body">
			<div align="left">
				<form action="member" method="post">
				<input type="hidden" name="param" value="updateMember">
					<table>
						<tr>
							<th>ID</th>
							<td>
								<input type="text" id="id" name="id" class="form-control input-lg" value="<%=mem.getId() %>" size="40" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th rowspan="2">Password&nbsp;&nbsp;</th>
							<td>
								<input type="password" id="pwd" name="pwd" class="form-control input-lg" placeholder="비밀번호 인력난" size="40">
							</td>
						</tr>
						<tr>
							<td>
								<input type="password" id="pwdcheck" class="form-control input-lg" name="pwdcheck" placeholder="비밀번호를 다시 입력해주세요." size="40">
							</td>
						</tr>
						<tr>
							<th>이름</th>
							<td>
								<input type="text" id="name" value="<%=mem.getName() %>" class="form-control input-lg" name="name" size="40">&nbsp;
							</td>
						</tr>
						<tr>
							<th>성별</th>
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
								<input type="button" class="form-control input-lg" style="padding-top:5px;width:90pt;height:30pt;font-size:15px;font-family: 'Noto Sans KR', sans-serif;" onclick="Postcode()" value="주소찾기">
								<div>등록된 주소 : <%=mem.getLocation() %></div>
								<input type="text" name="address" id="address" class="form-control input-lg" placeholder="주소" size="40">
								<input type="text" name="detailAddress" class="form-control input-lg" id="detailAddress" onchange="checkMap()" placeholder="상세주소" size="40">
								<input type="text" id="extraAddress" class="form-control input-lg" placeholder="참고항목" size="40">
								<div id="map"></div>
							</td>
						</tr>
						<tr>
							<th>핸드폰번호&nbsp;&nbsp;</th>
							<td>
								<input type="text" id="phone" name="phone" class="form-control input-lg" value="<%=mem.getPhone() %>" size="40" placeholder="하이픈(-)제외">
							</td>
						</tr>
						<tr>
							<th>이메일주소&nbsp;&nbsp;</th>
							<td>
								<input type="text" id="email" name="email" class="form-control input-lg" value="<%=mem.getEmail() %>" size="40" placeholder="이메일주소를 입력하세요">
							</td>
						</tr> 
						<tr>
							<th>카테고리</th>
							<td>
								<select class="form-control" style="width:330px" name="category">
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
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td style="text-align: center;" colspan="2">
								<input type="submit" onclick="send()" class="form-control input-lg" value="수정하기" style="background-color:#343a40;width:150pt;height:50pt;color:#ffff;font-size:20px;font-family: 'Noto Sans KR', sans-serif; padding: 20px;">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
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

	$('#category option[value="<%=mem.getCategory()%>"]').prop('selected', true);
});
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
