<%@page import="dto.MemberDto"%>
<%@page import="db.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
DBConnection.initConnection();
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
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- 글씨체추가 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<!-- css추가 -->
<link rel="stylesheet" href="style.css">
<title>createGroup</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- navbar 아이콘 추가 -->
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<!-- 부트스트랩 추가 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<style type="text/css">
th{
	border-color: gray;
	text-align: center;
}
td{
	padding-left: 5px;
}
.main{
	margin-top: 100px;
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
<body class="bbg-primary">
<div class="main" align="center">
  <div align="center" style="width:500px">
  	<div class="card shadow-lg border-0 rounded-lg mt-5">
  		<div class="card-header"><h3 class="text-center font-weight-light my-4">모임만들기</h3></div>
  			<div class="card-body">
				<form action="howdy" method="post" enctype="multipart/form-data">
				<input type="hidden" name="param" value="createGroupAf">
				<%
					if(mem != null){
				%>
					<input type="hidden" name="id" value="<%=mem.getId()%>">
				<%
					}
				%>
					<table>
						<tr>
							<th>카테고리</th>
							<td>
								<select class="form-control" style="width:300px" name="category">
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
							<th>지역</th>
							<td>
								<select class="form-control" style="width:300px" name="addressRegion" id="addressRegion1"></select>
			    				<select class="form-control" style="width:300px" name="addressDo" id="addressDo1"></select>
			    				<select class="form-control" style="width:300px" name="addressSiGunGu" id="addressSiGunGu1"></select>
							</td>
							<!--  
							<td>
								<input type="text" id="postcode" placeholder="우편번호">
								<input type="button" onclick="Postcode()" value="주소찾기"><br>
								<input type="text" name="address" id="address" placeholder="주소"><br>
								<input type="text" name="detailAddress" id="detailAddress" onchange="checkMap()" placeholder="상세주소"><br>
								<input type="text" id="extraAddress" placeholder="참고항목"><br>
								<div id="map"></div>
							</td>
							  -->
						</tr>
						<tr>
							<th>모임명</th>
							<td>
								<input type="text" class="form-control input-lg" name="groupName" placeholder="모임명을 입력하세요">
							</td>
						</tr>
						<tr>
							<th>모임제목</th>
							<td>
								<input type="text" class="form-control input-lg" name="groupTitle" placeholder="모임주제를 입력하세요">
							</td>
						</tr>
						<tr>
							<th>모임소개</th>
							<td>
								<textarea rows="10"  cols="35" name="groupContent" placeholder="내용을 입력하세요"></textarea>
							</td>
						</tr>
						<tr>
							<th>이미지 업로드&nbsp;&nbsp;&nbsp;</th>
							<td>
								<label>
									<input type="file" class="input-file-button" name="image" value="이미지선택">
								</label>
							</td>
						</tr>
					</table><br>
					<button type="submit" class="btn btn-warning">등록완료</button>
		&nbsp;&nbsp;<button type="button" id="btn-default" class="btn btn-default" onclick="location.href='main.jsp'">등록취소</button> <!-- 메인화면이동 -->
				</form>
			</div>
		</div>
	</div>
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> <!-- 우편변호 -->
<script>
$(document).ready(function(){
	$('#icon').click(function(){
		$('ul').toggleClass('show');	
	});
});
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
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}
</script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=397303b6b1a3824cfbfe07f9d7527206&libraries=services"></script> <!-- 카카오지도 -->
<script>
////////////////////////////지도 JS영역////////////////////////////////
function checkMap() {
	let Myaddress = document.getElementById('address').value;//  주소를 가져온다. by이기동**
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	    mapOption = {
	        center: new kakao.maps.LatLng(37.5556326132925, 126.992217614712), // 지도의 중심좌표
	        level: 5 // 지도의 확대 레벨
	    };  
	
	mapContainer.style.width = "400px";
	mapContainer.style.height = "400px";
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(Myaddress, function(result, status) {	// 주소를 입력한다. by이기동**
		
		var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커이미지의 주소입니다    
	    imageSize = new kakao.maps.Size(40, 40), // 마커이미지의 크기입니다
	    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
	    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
	    
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {

	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);	// *********검색한 주소의 좌표가져온다
			//alert(result[0].y+" "+result[0].x);
	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
        	});

	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+ Myaddress +'</div>'
	        });
	        
	        infowindow.open(map, marker);

	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    } 
	});    
}
</script>

<script type="text/javascript">
/////////////////////////////////////지역선택////////////////////////////////////////
$(function(){
    areaSelectMaker("#addressRegion1", "#addressDo1", "#addressSiGunGu1");
    areaSelectMaker("#addressRegion2", "#addressDo2", "#addressSiGunGu2");
});

var areaSelectMaker = function(a1, a2, a3){
    if(a1 == null || a2 == null || a3 == null){
        console.warn("Unkwon Area Tag");
        return;
    }

    var area = {
        "수도권" :{
            "서울특별시" : [ "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구" ],
            "경기도" : [ "수원시 장안구", "수원시 권선구", "수원시 팔달구", "수원시 영통구", "성남시 수정구", "성남시 중원구", "성남시 분당구", "의정부시", "안양시 만안구", "안양시 동안구", "부천시", "광명시", "평택시", "동두천시", "안산시 상록구", "안산시 단원구", "고양시 덕양구", "고양시 일산동구",
                "고양시 일산서구", "과천시", "구리시", "남양주시", "오산시", "시흥시", "군포시", "의왕시", "하남시", "용인시 처인구", "용인시 기흥구", "용인시 수지구", "파주시", "이천시", "안성시", "김포시", "화성시", "광주시", "양주시", "포천시", "여주시", "연천군", "가평군",
                "양평군" ],
            "인천광역시" : [ "계양구", "미추홀구", "남동구", "동구", "부평구", "서구", "연수구", "중구", "강화군", "옹진군" ]			
        },
        "강원권" :{
            "강원도" : [ "춘천시", "원주시", "강릉시", "동해시", "태백시", "속초시", "삼척시", "홍천군", "횡성군", "영월군", "평창군", "정선군", "철원군", "화천군", "양구군", "인제군", "고성군", "양양군" ]			
        },
        "충청권" :{
            "충청북도" : [ "청주시 상당구", "청주시 서원구", "청주시 흥덕구", "청주시 청원구", "충주시", "제천시", "보은군", "옥천군", "영동군", "증평군", "진천군", "괴산군", "음성군", "단양군" ],
            "충청남도" : [ "천안시 동남구", "천안시 서북구", "공주시", "보령시", "아산시", "서산시", "논산시", "계룡시", "당진시", "금산군", "부여군", "서천군", "청양군", "홍성군", "예산군", "태안군" ],
            "대전광역시" : [ "대덕구", "동구", "서구", "유성구", "중구" ],
            "세종특별자치시" : [ "세종특별자치시" ]			
        },
        "전라권" :{
            "전라북도" : [ "전주시 완산구", "전주시 덕진구", "군산시", "익산시", "정읍시", "남원시", "김제시", "완주군", "진안군", "무주군", "장수군", "임실군", "순창군", "고창군", "부안군" ],
            "전라남도" : [ "목포시", "여수시", "순천시", "나주시", "광양시", "담양군", "곡성군", "구례군", "고흥군", "보성군", "화순군", "장흥군", "강진군", "해남군", "영암군", "무안군", "함평군", "영광군", "장성군", "완도군", "진도군", "신안군" ],
            "광주광역시" : [ "광산구", "남구", "동구", "북구", "서구" ]			
        },
        "경상권" : {
            "경상북도" : [ "포항시 남구", "포항시 북구", "경주시", "김천시", "안동시", "구미시", "영주시", "영천시", "상주시", "문경시", "경산시", "군위군", "의성군", "청송군", "영양군", "영덕군", "청도군", "고령군", "성주군", "칠곡군", "예천군", "봉화군", "울진군", "울릉군" ],
            "경상남도" : [ "창원시 의창구", "창원시 성산구", "창원시 마산합포구", "창원시 마산회원구", "창원시 진해구", "진주시", "통영시", "사천시", "김해시", "밀양시", "거제시", "양산시", "의령군", "함안군", "창녕군", "고성군", "남해군", "하동군", "산청군", "함양군", "거창군", "합천군" ],
            "부산광역시" : [ "강서구", "금정구", "남구", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구", "해운대구", "기장군" ],
            "대구광역시" : [ "남구", "달서구", "동구", "북구", "서구", "수성구", "중구", "달성군" ],
            "울산광역시" : [ "남구", "동구", "북구", "중구", "울주군" ]			
        },
        "제주권" : {
            "제주특별자치도" : [ "서귀포시", "제주시" ]			
        }
    };

    //초기화
    init(true, true);

    //권역 기본 생성
    var areaKeys1 = Object.keys(area);
    areaKeys1.forEach(function(Region){
        $(a1).append("<option value="+Region+">"+Region+"</option>");
    });

    //변경 이벤트
    $(document).on("change", a1, function(){
        init(false, true);
        var Region = $(this).val();
        var keys = Object.keys(area[Region]);
        keys.forEach(function(Do){
            $(a2).append("<option value="+Do+">"+Do+"</option>");    
        });
    }).on("change", a2, function(){
        init();
        var Region = $(a1).val();
        var Do = $(this).val();
        var keys = Object.keys(area[Region][Do]);
        keys.forEach(function(SiGunGu){
            $(a3).append("<option value="+area[Region][Do][SiGunGu]+">"+area[Region][Do][SiGunGu]+"</option>");    
        });
    });

    function init(first, second){
        first ? $(a1).empty().append("<option value=''>선택</option>") : "";
        second ? $(a2).empty().append("<option value=''>선택</option>") : "";
        $(a3).empty().append("<option value=''>선택</option>");
    }
}

</script>
</body>
</html>