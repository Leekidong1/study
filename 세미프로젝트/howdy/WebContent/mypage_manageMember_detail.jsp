<%@page import="dto.MemberDto"%>
<%@page import="db.DBConnection"%>
<%@page import="dao.MeetingDao"%>
<%@page import="dto.MeetingDto"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        
<%
int group_seq = Integer.parseInt(request.getParameter("seq"));
MemberDto mem = (MemberDto)session.getAttribute("login");
if(mem == null){
%>  
	<script>
	alert("로그인 해 주십시오");
	location.href = "login.jsp";
	</script>	
<%
}
%>

<!DOCTYPE html>
<html>
<title>my page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- css 추가-->
<link href="css/styles-simple-sidebar.css" rel="stylesheet" />

<style type="text/css">
.logo{
 	display: inline-block;
 	padding-right: 500px; 
}
.main{
	margin-top: 80px;
}
.card{
	display: inline-block;
	margin: 15px;
}
.card a{
	width: 80px;
	height: 32px;
	font-size: 13px;
}
table{
	text-align: center;
}
#regilist{
	margin-bottom: 200px;
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
		if(mem.getAuth() == 1){
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
	<div style="margin-left:15%">
		
		<div class="w3-container">
			<div align="center"></div>
			
			<h4 style="margin-top: 50px">가입대기 중인 회원</h4>
			<table id="regilist" border="1">
				<col width="80"><col width="80"><col width="150"><col width="100">
				<tr>
					<th>아이디</th><th>이름</th><th>가입일</th><th>가입여부</th>
				</tr>
			</table>
			<h4>모임회원</h4>
			<table id="managelist" border="1">
				<col width="80"><col width="80"><col width="150"><col width="100"><col width="100">
				<tr>
					<th>아이디</th><th>이름</th><th>가입일</th><th>권한부여</th><th>관리</th>
				</tr>
			</table>
		</div>
	</div>
</div>
<button onclick=""></button>
<script type="text/javascript">
$(document).ready(function() {
	$.ajax({	// 비동기통신
		url:"./GroupMemberController?param=manageMember",	// or url:"member?param=idcheck",
		type:"post",
		data:{ "group_seq":'<%=group_seq%>' },
		datatype:"json",
		success:function(data){
		//	let str = JSON.stringify(data);	// 전체확인하기
		//	alert(str);
					 
			 $.each(data.Rlist, function(index, item) { // 가입대기중인회원			
				let showlist = '<tr>'+
							   		'<td>'+
							   			item.id +
							   		'</td>'+
							   		'<td>'+
						   				item.name +
						   			'</td>'+
						   			'<td>'+
					   					item.joindate +
					   				'</td>'+
					   				'<td>'+
				   						'<select onchange="regi(this.value)">' +
				   							'<option>선택</option>' +
				   							'<option value="<%=group_seq%> ' + item.id + ' 1">허락</option>' +
				   							'<option value="<%=group_seq%> ' + item.id + ' 0">거부</option>' +
				   						'</select>' +
				   					'</td>'+
							   '</tr>';	
							   								
				$("#regilist").append(showlist);
			 }); 
			 
			 $.each(data.Mlist, function(index, item) { // 모임원
				let showlist = '<tr>'+
								   		'<td>'+
							   				item.id +
								   		'</td>'+
								   		'<td>'+
							   				item.name +
							   			'</td>'+
							   			'<td>'+
											item.joindate +
										'</td>'+
										'<td>'+
											'<select onchange="regi(this.value)">' +
												'<option>선택</option>' +
												'<option value="<%=group_seq%> ' + item.id + ' 1">모임원</option>' +
												'<option value="<%=group_seq%> ' + item.id + ' 2">부모임장</option>' +
											'</select>' +
										'</td>'+
										'<td>'+
											'<button onclick="deleteGroupMem(this.value)" value="<%=group_seq%> ' + item.id + ' 0">추방</button>' +
										'</td>'+
							   '</tr>';	
								
				$("#managelist").append(showlist);
			 }); 
			
		},
		error:function(){
			alert(error);
		}
	});
});

function deleteGroupMem(value) {
	location.href = 'GroupMemberController?param=deleteGroupMem&groupMem=' + value;
}

function regi(value) {
	location.href = 'GroupMemberController?param=changeGroupMemAuth&groupMemAuth=' + value;
}
</script>
      
</body>
</html>