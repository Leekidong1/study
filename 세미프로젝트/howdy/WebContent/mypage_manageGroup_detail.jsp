
<%@page import="util.CalUtil"%>
<%@page import="dto.MemberDto"%>
<%@page import="db.DBConnection"%>
<%@page import="dao.MeetingDao"%>
<%@page import="dto.MeetingDto"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        
<%
int seq = Integer.parseInt(request.getParameter("seq"));
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

<%!
public boolean nvl(String msg){
	return msg==null || msg.trim().equals("")?true:false;
}


// 날짜, 일별일정
public String callist(int year, int month, int day){
	String str = "";
	
	str += String.format("&nbsp;<a href='#?year=%d&month=%d&day=%d'>", year, month, day); 
	str += String.format("%2d", day);
	str += "</a>";
	
	return str;
}

// 날짜별 리스트
public String makeTable(int seq, int year, int month, int day, List<MeetingDto> list){
	String str = "";
	
	// 2021 6 18	-> 20210618
	String dates = (year + "") + CalUtil.two(month + "") + CalUtil.two(day + "");
	
	str +="<table>";
	for(MeetingDto dto : list){
		String date = dto.getSdate().substring(0, 8);
		if(date.equals(dates)){
			str += "<tr><td style='padding: 0px; border:1;background-color: red; border-color: blue; radius=3'>";		
			str += "<a href='mypage_meeting_detail.jsp?Mseq=" + dto.getMeeting_seq() + "&Gseq=" + seq + "'>";
			str += "<font style='font-size:12px; color:white'>";
			str += dot3(dto.getTitle());
			str += "</font>";
			str += "</a>"; 
			str += "</td></tr>";			
		}		
	}		
	str +="</table>";
	
	return str;
}

// 일정의 제목이 길때 ...으로 처리하는 함수	
public String dot3(String msg){
	String str = "";
	if(msg.length() >= 10){
		str = msg.substring(0, 10);
		str += "...";
	}else{
		str = msg.trim();
	}
	return str;
}

%>  


<!DOCTYPE html>
<html>
<title>my page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- css추가-->
<link href="css/styles-simple-sidebar.css" rel="stylesheet" />

<!-- 달력 숫자에 밑줄이 생겨서 추가했습니다 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- css 추가 -->
<style type="text/css">

<style type="text/css">
.logo{
 	display: inline-block;
 	padding-right: 500px; 
}
.main{
	margin-top: 80px;
}
.showstep1{
    max-height: 100px;
    overflow: hidden;
}
.showstep2{
    max-height: 600px;
    overflow: hidden;
}
.content{
    height: 100px;
    
}
.hide{
    display: none;
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
<div class="d-flex" id="wrapper" style="width: 1800px;">
	<!-- Sidebar 추가했습니다-->
	<div class="border-end bg-white" id="sidebar-wrapper">
		<div class="sidebar-heading border-bottom bg-light">Menu</div>
		<div class="list-group list-group-flush">
			<a class="list-group-item list-group-item-action list-group-item-light p-3" href="mypage_info.jsp">개인정보관리</a>
			<a class="list-group-item list-group-item-action list-group-item-light p-3" href="mypage_mylist.jsp">내 모임 리스트</a>
			<a class="list-group-item list-group-item-action list-group-item-light p-3" href="mypage_calendar.jsp">내 일정</a>
	  <% 
	  	if(mem.getGroup_auth() == 3){
	  %>
	  <a class="list-group-item list-group-item-action list-group-item-light p-3" href="mypage_manageGroup.jsp">운영중인 모임</a>
	  <%
	  	}
	  %>
	</div>
</div>	
	<!-- Page content wrapper-->
	<div id="page-content-wrapper">
	
	
<%
DBConnection.initConnection();
Calendar cal = Calendar.getInstance();

cal.set(Calendar.DATE, 1);

String syear = request.getParameter("year");
String smonth = request.getParameter("month");

int year = cal.get(Calendar.YEAR);
if(nvl(syear) == false){	// 파라미터로 넘어 온 데이터가 있는 경우
	year = Integer.parseInt(syear);
}

int month = cal.get(Calendar.MONTH) + 1;
if(nvl(smonth) == false){
	month = Integer.parseInt(smonth);
}

if(month < 1){
	month = 12;
	year--;
}
if(month > 12){
	month = 1;
	year++;
}



cal.set(year, month - 1, 1); 
int dayOfweek = cal.get(Calendar.DAY_OF_WEEK);

// year --;
String pp = String.format("<a href='%s?year=%d&month=%d&seq=%d'><img src='image/left.gif' width='20px' height='20px'></a>", 
						"mypage_manageGroup_detail.jsp",year-1, month, seq);
// month --;
String p = String.format("<a href='mypage_manageGroup_detail.jsp?year=%d&month=%d&seq=%d'><img src='image/prec.gif' width='20px' height='20px'></a>", 
							year, month-1, seq);
// month ++;
String n = String.format("<a href='mypage_manageGroup_detail.jsp?year=%d&month=%d&seq=%d'><img src='image/next.gif' width='20px' height='20px'></a>", 
							year, month+1, seq);
// year ++;
String nn = String.format("<a href='mypage_manageGroup_detail.jsp?year=%d&month=%d&seq=%d'><img src='image/last.gif' width='20px' height='20px'></a>", 
							year+1, month, seq);




MeetingDao dao = MeetingDao.getInstance();
													
List<MeetingDto> list = dao.getCalendarList(seq, year + CalUtil.two(month + ""));
if(list != null && list.size() != 0){
	System.out.println(list.get(0).getRdate());
}
%>
	
	<div class="w3-container">
	<div align="center"></div>
	<h2>운영중인 모임</h2>
		<button type="button" onclick="location.href='mypage_meetingwrite.jsp?seq=<%=seq%>'">일정추가</button>
	</div>
	
		
	<table class="table table-bordered" style="width: 65%">
	<col width="100"><col width="100"><col width="100"><col width="100">
	<col width="100"><col width="100"><col width="100">
	<tr>
		<td colspan="7" align="center" style="padding-top: 20px;">
			<%=pp %>&nbsp;&nbsp;<%=p %>&nbsp;&nbsp;
			
			<font color="black" style="font-size: 35px">
				<%=String.format("%d년&nbsp;&nbsp;%d월", year, month) %>
			</font>
			
			<%=n %>&nbsp;&nbsp;<%=nn %>&nbsp;&nbsp;
		</td>
	</tr>
	<tr height="50" style="background-color: #f0f0f0;color: white;">
		<th class="text-center">일</th> 
		<th class="text-center">월</th>
		<th class="text-center">화</th>
		<th class="text-center">수</th>
		<th class="text-center">목</th>
		<th class="text-center">금</th>
		<th class="text-center">토</th>
	</tr>
	
	<tr height="100" align="left" valign="top">
	<%
	// 윗쪽 빈칸
	for(int i = 1;i < dayOfweek; i++){
		%>
		<td style="background-color: #cecece;">&nbsp;</td>
		<%
	}
	
	// 날짜
	int lastday = cal.getActualMaximum(Calendar.DATE);
	for(int i = 1;i <= lastday; i++){
	%>
	<td>
		<%=callist(year, month, i) %>&nbsp;&nbsp;
		<%=makeTable(seq,year,month,i,list) %>
	</td>
	<% 
		
		if( (i + dayOfweek - 1)%7 == 0 && i != lastday){
			%>
			</tr><tr height="100" align="left" valign="top">
			<%	
		}	
	}
	// 밑의 빈칸
	cal.set(Calendar.DATE, lastday);
	int weekday = cal.get(Calendar.DAY_OF_WEEK);
	for(int i = 0;i < 7 - weekday; i++){
		%>
		<td style="background-color: #cecece">&nbsp;</td>
		<%
	}
	%>	
	</tr>	
	</table>
	</div>
</div>
      
</body>
</html>
    



