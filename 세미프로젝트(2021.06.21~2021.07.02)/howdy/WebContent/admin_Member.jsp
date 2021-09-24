<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<style type="text/css">
 td{
 	padding: 5px;
 }
 .create{
 	margin-right: 455px;
 }
 .logo{
	display: inline-block;
	padding-right: 500px; 
 }
 #searching{
	margin-top: 100px;
	margin-left: 500px;
 }
 #showList{
	margin: auto;
} 
table.table
    thead
        tr
            th(scope='col') 번호
            th(scope='col') 회원번호
            th(scope='col') 아이디
            th(scope='col') 이름 
            th(scope='col') 성별 
            th(scope='col') 전화번호 
            th(scope='col') 이메일 
            th(scope='col') 주소 
            th(scope='col') 관심카테고리 
            th(scope='col') 등록일 
            th(scope='col') 
.container{
	margin-top: 100px;
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
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
  <script>
  $( function() {
    $( "#datepicker1" ).datepicker({
    	dateFormat: "yy-mm-dd"
    });
    $( "#datepicker2" ).datepicker({
    	dateFormat: "yy-mm-dd"
      });
  } );
  </script>
</head>
<body>
<nav>
	<label class="logo"><img src="image/howdy003.png"></label>
		<ul>
  		<li><a href="main.jsp" class="text-light">#메인이동</a><li>
  		<li><a href="admin_Member.jsp" class="text-light">#회원관리</a><li>
  		<li><a href="admin_Group.jsp" class="text-light">#모임관리</a><li>
  		<li><a href="login.jsp" class="text-light">#로그아웃</a><li>
  		</ul>
  			<label id="icon">
		<i class="fas fa-bars"></i>
	</label>
</nav>	
	<div id="searching" align="center">
			<form class="form-inline" action="/action_page.php"> <!-- 추가 73~90까지 <form>태그포함 안에 내용싹다 복붙! -->
	<div class="form-group"> 
			<label for="email">개설일 </label><input type="text" class="form-control form-control-sm" id="datepicker1"> ~ <input type="text" class="form-control form-control-sm" id="datepicker2"><br>
		</div>&nbsp;&nbsp;&nbsp;
				<div class="form-group ">
	
			<select id="category" class="form-control form-control-sm">
				<option value="">검색선택</option>
				<option value="id">아이디</option>
				<option value="name">이름</option>
				<option value="address">주소</option>
				<option value="interest">카테고리</option>
			</select>
			<input type="text" id="searchText" class="form-control form-control-sm form-control-borderless" placeholder="입력란">
			<input type="button" id="search" class="btn btn-warning btn-sm" value="검색">
		</div>
	</div>
	</form>	
	<div align="right">
		<span class="create">
			<button class="btn btn-warning" onclick="location.href='regi.jsp'">회원추가</button>
		</span>
	</div><br>
<div align="center">
	<table id="showList" class="table">
		<col width="40"><col width="40"><col width="100"><col width="60"><col width="60">
		<col width="100"><col width="150"><col width="200"><col width="100"><col width="170"><col width="100">
		<thead>
			<tr>
            <th scope="col">번호</th>
            <th scope="col">회원번호</th>
            <th scope="col">아이디</th>
            <th scope="col">이름</th>
            <th scope="col">성별</th>
            <th scope="col">전화번호</th>
            <th scope="col">이메일</th>
            <th scope="col">주소</th>
            <th scope="col">관심카테고리</th>
            <th scope="col">등록일</th>
            <th scope="col"> </th> 
        	</tr>
        </thead>
	</table>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$.ajax({	// 비동기통신
		url:"./member?param=admin_Member",	// or url:"member?param=idcheck", 
		type:"post",
		data:{ "searchText":'', "startdate":'', "enddate":'', "category":'' },
		datatype:"json",
		success:function(data){
		//	let str = JSON.stringify(data);	// 전체확인하기
		//	alert(str);
			let i = 1;
			$.each(data.list, function(index, item) {
				let showlist =	'<tr>' +
									'<th>'+
										i++ +
									'</th>' +
									'<td>' +
										item.seq + 
									'</td>' +
									'<td>' +
										item.id + 
									'</td>' +
									'<td>' +
										item.name + 
									'</td>' +
									'<td>' +
										item.gender + 
									'</td>' +
									'<td>' +
										item.phone + 
									'</td>' +
									'<td>' +
										item.email + 
									'</td>' +
									'<td>' +
										item.location + 
									'</td>' +
									'<td>' +
										item.category + 
									'</td>' +
									'<td>' +
										item.joindate + 
									'</td>' +
									'<td>' +
										'<button name="Btns1" itemId=' + item.id + ' class="btn btn-warning btn-sm">수정 </button>&nbsp;&nbsp;' +
										'<button name="Btns2" itemId=' + item.id + ' param=deleteMemberAf class="btn btn-warning btn-sm"> 삭제</button>' +
									'</td>' +
								 '</tr>';
				$("table").append(showlist);
			});
		},
		error:function(){
			alert('error');
		}
	});
	
	/* 0701 175~180 추가 */
	$(document).on("click", "button[name='Btns1']", function () {		
		location.href="admin_Member_update.jsp?id=" +  $(this).attr("itemId");
	});
	$(document).on("click", "button[name='Btns2']", function () {		
		location.href="member?id=" + $(this).attr("itemId") + "&param=" + $(this).attr("param");
	});
	
	$("#search").click(function() {
		$("table").empty();
		
		let setTable = '<col width="40"><col width="80"><col width="130"><col width="80"><col width="100">' +
					   '<col width="120"><col width="180"><col width="120"><col width="100"><col width="100">' +
					   '<thead><tr>' +
					   	 '<th scope="col">번호</th><th scope="col">회원번호</th><th scope="col">아이디</th><th scope="col">이름</th>' +
					   	 '<th scope="col">성별</th><th scope="col">전화번호</th><th>이메일</th>' +
					     '<th scope="col">주소</th><th scope="col">관심카테고리</th><th scope="col">등록일</th><th scope="col"> </th>' +
					   '</thead></tr>'; 
		$("table").append(setTable);
					   
		let category = $("#category").val();
		let searchText = $("#searchText").val();
		let startdate = $("#datepicker1").val();
		let enddate = $("#datepicker2").val();
		
		$.ajax({	// 비동기통신
			url:"./member?param=admin_Member",	// or url:"member?param=idcheck", 
			type:"post",
			data:{ "searchText":searchText, "startdate":startdate, "enddate":enddate, "category":category },
			datatype:"json",
			success:function(data){
			//	let str = JSON.stringify(data);	// 전체확인하기
			//	alert(str);	 
				let i = 1;
				$.each(data.list, function(index, item) {
					let showlist =	'<tr>' +
										'<th>'+
											i++ +
										'</th>' +
										'<td>' +
											item.seq + 
										'</td>' +
										'<td>' +
											item.id + 
										'</td>' +
										'<td>' +
											item.name + 
										'</td>' +
										'<td>' +
											item.gender + 
										'</td>' +
										'<td>' +
											item.phone + 
										'</td>' +
										'<td>' +
											item.email + 
										'</td>' +
										'<td>' +
											item.location + 
										'</td>' +
										'<td>' +
											item.category + 
										'</td>' +
										'<td>' +
											item.joindate + 
										'</td>' +
										'<td>' +
											'<button name="Btns1" itemId=' + item.id + ' class="btn btn-warning btn-sm">수정 </button>&nbsp;&nbsp;' +
											'<button name="Btns2" itemId=' + item.id + ' param=deleteMemberAf class="btn btn-warning btn-sm"> 삭제</button>' +
										'</td>' +
									'</tr>';
					$("table").append(showlist);
					$("td").css("padding","10px");
				});
			},
			error:function(){
				alert('error');
			}
		});
		/* 0701 257~262 추가 */
		$(document).on("click", "button[name='Btns1']", function () {		
			location.href="admin_Member_update.jsp?id=" +  $(this).attr("itemId");
		});
		$(document).on("click", "button[name='Btns2']", function () {		
			location.href="member?id=" + $(this).attr("itemId") + "&param=" + $(this).attr("param");
		});	
	});
	
});
</script>
</body>
</html>