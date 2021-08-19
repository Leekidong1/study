<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
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
<title>MyPage_mylist</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- css 추가-->
<link href="css/styles-simple-sidebar.css" rel="stylesheet" />
<!-- 글씨체추가 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- navbar -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

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
h1 {
	margin: 20px;
}
*{
	padding: 0;
	margin: 0;
	text-decoration: none;
	list-style: none;
}
body{
	font-family: 'Noto Sans KR', sans-serif;
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
<!-- Sidebar -->
<div class="d-flex" id="wrapper">
	<div class="border-end bg-white" id="sidebar-wrapper">
		<div class="sidebar-heading border-bottom bg-light">Menu</div>
		<div class="list-group list-group-flush">
		    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="mypage_info.jsp">개인정보관리</a>
		    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="mypage_mylist.jsp">내 모임 리스트</a>
		    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="mypage_calendar.jsp">내 일정</a>
			  <% 
			  	if(mem.getGroup_auth() == 3){
			  %>
			  <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">운영중인 모임</a>
			  <%
			  	}
			  %>
		</div>
	</div>		
	<!-- Page content wrapper-->
       <div id="page-content-wrapper">
		<div class="w3-container">
			<h1>내 모임 리스트</h1>
			<hr>
			<div id="refrence"></div>	
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$.ajax({	// 비동기통신
		url:"./control?param=mypage_mylist",	// or url:"member?param=idcheck",
		type:"post",
		data:{ "id":'<%=mem.getId()%>' },
		datatype:"json",
		success:function(data){
		//	let str = JSON.stringify(data);	// 전체확인하기
		//	alert(str);
					 
			 $.each(data.list, function(index, item) {
								<!-- 카드의 테두리 : card -->
				let showlist =	'<div class="card" style="max-width: 20rem;">' +
								    <!-- 카드 상단에 배치되는 이미지 : card-img-top -->
								    '<img class="card-img-top" src="http://localhost:8090/howdy/imageServer/' + item.newfileName + '" alt="...">' +
								    <!-- 카드본문:card-body -->
								    '<div class="card-body">' +
								      <!-- 카드 제목 :card-title -->
								      '<h4 class="card-title">' + item.groupName + '</h4>' +
								      <!-- 카드의 내용 글 :card-text -->
								      '<p class="card-text">' + item.groupTitle + '</p>' +
								     '<a href="detail_main_groupdetail.jsp?seq=' +item.groups_seq + '" class="btn btn-primary">모임상세</a>&nbsp;&nbsp;' +
								    '</div>' +
								 '</div>';
								
				$("#refrence").append(showlist);
			}); 
			
		},
		error:function(){
			alert(error);
		}
	});
});
</script>      
</body>
</html>
    