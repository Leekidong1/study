<%@page import="dto.MemberDto"%>
<%@page import="db.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
DBConnection.initConnection();
MemberDto dto = (MemberDto)session.getAttribute("login");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>Insert title here</title>
<style type="text/css">
#refrence{
	margin-right: 700px;
}
#searching{
	background-color: #FAFAFA;
	border-radius: 15px;
	box-shadow: 5px 5px 8px #D8D8D8;
	width: 600px;
	height: 280px;
	padding: 30px;
	margin: 20px;
	margin-top: 20px;
	margin-right: 700px;
}
.main{
	display: inline-block;
	vertical-align: top;
}
table{
	width: 500px;
	height: 300px;
	margin-right: 500px;
	margin-top: 80px;
	border-collapse: collapse;
	border-color: #C17B7B;
}
td{
	padding: 20px;
}
#map{
	position: fixed;
	right: 150px;
	bottom:10px;
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
#searchBox{
	margin: 5px;
}
#datepicker1, #datepicker2{
	border-radius: 5px;
	border: 1px solid #D8D8D8;
	box-shadow: 3px 3px 6px #D8D8D8;
	
}
.customoverlay {position:relative;bottom:85px;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;float:left;}
.customoverlay:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}
.customoverlay a {display:block;text-decoration:none;color:#000;text-align:center;border-radius:6px;font-size:14px;font-weight:bold;overflow:hidden;background: #d95050;background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;}
.customoverlay .title {display:block;text-align:center;background:#fff;margin-right:35px;padding:2px 15px;font-size:14px;font-weight:bold;}
.customoverlay:after {content:'';position:absolute;margin-left:-12px;left:50%;bottom:-12px;width:22px;height:12px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
</style>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
<div align="center">
	<nav>
	<label class="logo"><img src="image/howdy003.png"></label>
	<ul>
		<li><a href="main.jsp">Home</a><li>
		<li><a href="main_createGroup.jsp">모임만들기</a><li>
		<li><a href="main_findGroup_main.jsp">모임찾기</a><li>
	<%
		if(dto != null){
	%>
		<li><a href="mypage_info.jsp">MyPage</a><li>
		<li><a href="login.jsp">로그아웃</a><li>
	<%
		if(dto.getAuth() == 1){
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
	<div id="searching" align="center">
		<h4>모임 및 일정을 찾아주세요</h4>
		<span id="searchBox">
			<img alt="" src="image/calendar.png" style="width: 35px; height: 35px;">
			<input type="text" id="datepicker1"> ~ <input type="text" id="datepicker2">
		</span><br> 
		<span id="searchBox">
			<select id="category" class="form-control">
				<option value="">카테고리 선택</option>
				<option value="sports">운동/스포츠</option>
				<option value="entertaiment">문화/공연/축제</option>
				<option value="readBook">책/글/인문학</option>
				<option value="language">외국/언어</option>
				<option value="vehicle">차/오토바이</option>
				<option value="game">게임</option>
				<option value="cook">요리</option>
				<option value="pets">반려동물</option>
			</select>
		</span>
		<span id="searchBox">
			<input type="text" id="address" class="form-control input-lg" placeholder="주소 및 지역 입력">
		</span>
		<span id="searchBox">
			<input type="button" class="btn btn-warning" id="search" value="검색" style="margin-top: 10px; width: 200px">
		</span>
	</div>
	<div>
		<div class="main">
			<div id="refrence">
			</div>
		</div>
		<div class="main" id="map"></div>
	</div>
</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=397303b6b1a3824cfbfe07f9d7527206&libraries=services"></script> <!-- 카카오지도 -->
<script type="text/javascript">
$(document).ready(function() {
	$.ajax({	// 비동기통신
		url:"./control?param=grouplist",	// or url:"member?param=idcheck",
		type:"post",
		data:{ "location":'', "category":'', "startdate":'', "enddate":'' },
		datatype:"json",
		success:function(data){
		//	let str = JSON.stringify(data);	// 전체확인하기
		//	alert(str);
					 
			$.each(data.Glist, function(index, item) {
				let showlist = 							
								'<div class="card" style="max-width: 25rem; text-align: left; margin: 30px;">' +
								<!-- 카드 상단에 배치되는 이미지 : card-img-top -->
								'<img class="card-img-top" src="http://localhost:8090/howdy/imageServer/' + item.newfileName + '" alt="...">' +
								    <!-- 카드본문:card-body -->
								    '<div class="card-body">' +
								      	<!-- 카드 제목 :card-title -->
								      	'<h4 class="card-title">' + item.groupName + '</h4>' +
								     	 <!-- 카드의 내용 글 :card-text -->
								      	'<p class="card-text"><font size="3"><b>주제:</b></font>&nbsp;&nbsp;' + item.groupTitle + '</p>' +
								      	'<p class="card-text"><font size="3"><b>평점:</b></font>&nbsp;&nbsp;' + item.starScore + '</p>' +
								      	'<p class="card-text"><font size="3"><b>지역:</b></font>&nbsp;&nbsp;' + item.location + '</p>' +
								      	'<p class="card-text"><font size="3"><b>종류:</b></font>&nbsp;&nbsp;' + item.category + '</p>' +
							     		'<a href="detail_main_groupdetail.jsp?seq=' +item.groups_seq + '" class="btn btn-primary">모임상세</a>' +
							    	'</div>' +
							 	'</div>';
								
				$("#refrence").append(showlist);
			});
			
			////////////////////////////지도 JS영역////////////////////////////////
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div
		    mapOption = {
		        center: new kakao.maps.LatLng(37.5556326132925, 126.992217614712), // 지도의 중심좌표
		        level: 9 // 지도의 확대 레벨
		    };  
		
			mapContainer.style.width = "800px";
			mapContainer.style.height = "880px";
			
			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			
			var imageSrc = 'image/placeholder1.png', // 마커이미지의 주소입니다    
		    imageSize = new kakao.maps.Size(45, 50), // 마커이미지의 크기입니다
		    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
			
			$.each(data.Mlist, function(index, item) {
				
				var latitude = parseFloat(item.latitude);
				var longitude = parseFloat(item.longitude);
				
				var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
				var markerPosition  = new kakao.maps.LatLng( latitude , longitude ); 
				
				// 마커를 생성합니다
				var marker = new kakao.maps.Marker({
				    position: markerPosition,
				    image: markerImage
				});
				
		     	// 마커가 지도 위에 표시되도록 설정합니다
		        marker.setMap(map);
		        
		     	// 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
		        var content = '<div class="customoverlay">' +
		            '  <a href="detail_main_groupdetail.jsp?seq=' + item.groups_seq + '" target="_self">' +
		            '    <span class="title">' + item.title + '</span>' +
		            '  </a>' +
		            '</div>';

		        // 커스텀 오버레이가 표시될 위치입니다 
		        var position = new kakao.maps.LatLng( latitude , longitude );  

		        // 커스텀 오버레이를 생성합니다
		        var customOverlay = new kakao.maps.CustomOverlay({
		            map: map,
		            position: position,
		            content: content,
		            yAnchor: 1 
		        });
		     	
			});
		
		},
		error:function(){
			
		}
	});
	
	$("#search").click(function() {
		$("#refrence").empty();
		
		let location = $("#address").val();
		let category = $("#category").val();
		let startdate = $("#datepicker1").val();
		let enddate = $("#datepicker2").val();
		
		$.ajax({	// 비동기통신
			url:"./control?param=grouplist",	// or url:"member?param=idcheck",
			type:"post",
			data:{ "location":location, "category":category, "startdate":startdate, "enddate":enddate },
			datatype:"json",
			success:function(data){
			//	let str = JSON.stringify(data);	// 전체확인하기
			//	alert(str);
				
			//	alert(data.list[0].groupName); // list안에 인덱스가 있는 경우
			//	alert(data.list[0].category);	
				 
				$.each(data.Glist, function(index, item) {
					let showlist =	
									'<div class="card" style="max-width: 25rem; text-align: left; margin: 30px;">' +
									<!-- 카드 상단에 배치되는 이미지 : card-img-top -->
									'<img class="card-img-top" src="http://localhost:8090/howdy/imageServer/' + item.newfileName + '" alt="...">' +
									    <!-- 카드본문:card-body -->
									    '<div class="card-body">' +
									      	<!-- 카드 제목 :card-title -->
									      	'<h4 class="card-title">' + item.groupName + '</h4>' +
									     	 <!-- 카드의 내용 글 :card-text -->
									      	'<p class="card-text"><font size="3"><b>주제:</b></font>&nbsp;&nbsp;' + item.groupTitle + '</p>' +
									      	'<p class="card-text"><font size="3"><b>평점:</b></font>&nbsp;&nbsp;' + item.starScore + '</p>' +
									      	'<p class="card-text"><font size="3"><b>지역:</b></font>&nbsp;&nbsp;' + item.location + '</p>' +
									      	'<p class="card-text"><font size="3"><b>종류:</b></font>&nbsp;&nbsp;' + item.category + '</p>' +
								     		'<a href="detail_main_groupdetail.jsp?seq=' +item.groups_seq + '" class="btn btn-primary">모임상세</a>' +
								    	'</div>' +
								 	'</div>';
					$("#refrence").append(showlist);
				}); 
				
				////////////////////////////지도 JS영역////////////////////////////////
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div
			    mapOption = {
			        center: new kakao.maps.LatLng(37.5556326132925, 126.992217614712), // 지도의 중심좌표
			        level: 9 // 지도의 확대 레벨
			    };  
			
				mapContainer.style.width = "800px";
				mapContainer.style.height = "880px";
				
				// 지도를 생성합니다    
				var map = new kakao.maps.Map(mapContainer, mapOption); 
				
				var imageSrc = 'image/placeholder1.png', // 마커이미지의 주소입니다    
			    imageSize = new kakao.maps.Size(45, 50), // 마커이미지의 크기입니다
			    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
				
				$.each(data.Mlist, function(index, item) {
					
					var latitude = parseFloat(item.latitude);
					var longitude = parseFloat(item.longitude);
					
					var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
					var markerPosition  = new kakao.maps.LatLng( latitude , longitude ); 
					
					// 마커를 생성합니다
					var marker = new kakao.maps.Marker({
					    position: markerPosition,
					    image: markerImage
					});
					
			     	// 마커가 지도 위에 표시되도록 설정합니다
			        marker.setMap(map);
			        
			     	// 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
			        var content = '<div class="customoverlay">' +
			            '  <a href="detail_main_groupdetail.jsp?seq=' + item.groups_seq + '" target="_self">' +
			            '    <span class="title">' + item.title + '</span>' +
			            '  </a>' +
			            '</div>';
	
			        // 커스텀 오버레이가 표시될 위치입니다 
			        var position = new kakao.maps.LatLng( latitude , longitude );  
	
			        // 커스텀 오버레이를 생성합니다
			        var customOverlay = new kakao.maps.CustomOverlay({
			            map: map,
			            position: position,
			            content: content,
			            yAnchor: 1 
			        });
			     	
				});
				
			},
			error:function(){
				
			}
		});
		
	});
});
</script>
</body>
</html>