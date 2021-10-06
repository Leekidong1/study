<%@page import="dto.MemberDto"%>
<%@page import="dto.GroupDto"%>
<%@page import="dao.GroupDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String param = (String)request.getAttribute("param");
if(param == null || param.equals("")){
	param = "detail_groupdesc";
}
MemberDto dto = (MemberDto)session.getAttribute("login");

String id = "";
String name = "";
if(dto != null){
	id = dto.getId();
	name = dto.getName();
}

int seq = Integer.parseInt(request.getParameter("seq"));
GroupDao groupdao = GroupDao.getInstance();
GroupDto groupinfo = groupdao.getGroup(seq);

Object choice = request.getAttribute("choice");
System.out.println("Object를 받았어요."+choice);
String pageNumber = request.getParameter("pageNumber");
if(choice == null || pageNumber == null){
	choice = "";
	pageNumber = "";
}
System.out.println("choice를 보낼게요."+choice);

%>    

<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> <!-- 0701 추가 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script> <!-- 0701 추가 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> <!-- 0701 추가 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>Insert title here</title>
<style type="text/css">
.logo{
 	display: inline-block;
 	padding-right: 500px; 
}
.main{
	margin-top: 100px;
}
.group_pic{
	width: 600px;
	height: 400px;
}
tr>td{
		text-align: center;
	}
	
table tr:hover{
		background-color: #E2E2E2; /* 0701추가 */
	}
table {
	max-width: 800px;
}

#gname {
text-align: center;
font-family: "Noto Sans KR";
font-size: 18px;
font-style: italic;
}

#gtitle {
text-align: center;
font-family: "Noto Sans KR";
font-size: 14px;
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

<div class="main" align="center">
	<table>
		<col width="110px"><col width="110px"><col width="110px"><col width="110px"><col width="360px"> <!-- total width  = 800px -->
		<tr height="80px">
			<th colspan="4" rowspan="2"><img alt="" src="http://localhost:8090/howdy/imageServer/<%=groupinfo.getNewfileName() %>" class="group_pic"></th>
			<td>
				<div id="gname" style="font-size: 20px; color: #FE9A2E;">모임명</div>
				<div id="gname"><%=groupinfo.getGroupName() %></div>
			</td>
		</tr>
		<tr height="200px">
			<td>
				<div id="gname" style="font-size: 18px; color: #FE9A2E;">모임소개</div>
				<div id="gtitle"><%=groupinfo.getGroupContent() %></div>
			</td>
		</tr>
		<tr><!-- 추후 a href로 교체... 클릭시 글자확대 -->
		<td><a href="control?param=groupdesc&seq=<%=seq%>" class= "btn btn-outline-dark btn-lg" role="button" style="font-size: 15px">모임일정</a></td> <!-- 0710 변경 -->
		<td><a href="Bbs?param=groupbbs&seq=<%=seq%>" class= "btn btn-outline-dark btn-lg" role="button" style="font-size: 15px">대화방</a></td> <!-- 0710 변경 -->
		<td><a href="ReviewController?param=groupreview&seq=<%=seq%>" class= "btn btn-outline-dark btn-lg" role="button" style="font-size: 15px">모임후기</a></td> <!-- 0710 변경 -->
		<td><a href="GroupMemberController?param=groupmember&seq=<%=seq%>" class= "btn btn-outline-dark btn-lg" role="button" style="font-size: 15px">모임멤버</a></td> <!-- 0710 변경 -->
		<%
			if(dto != null){
		%> 
		<td><input type="button" value="가입" style="margin-left: 40px; font-size: 15px; width: 100px" class= "btn btn-warning btn-lg" onclick="joingroup()"></td>
		<%
			}
		%>
		</tr>
		<tr>
			<td colspan="5">
			<%
				if(!param.equals("detail_bbs")){
			%>
	 		<jsp:include page='<%=param + ".jsp" %>'></jsp:include>
	 		<%
				}else{
	 		%>
	 		<jsp:include page='<%=param + ".jsp?seq=" + seq + "&choice=" + choice + "&pageNumber=" + pageNumber %>'></jsp:include>	
	 		<%
				}
	 		%>
	 		</td>
		</tr>
	</table>
</div>

<script type="text/javascript">
/* 임시 */
function groupdesc(){
	location.href = "control?param=groupdesc&seq=" + <%=seq%> ;
}
function groupbbs(){
	location.href = "Bbs?param=groupbbs&seq=" + <%=seq%> ;
}
function groupreview(){
	location.href = "ReviewController?param=groupreview&seq=" + <%=seq%> ;
}
function groupmember(){
	location.href = "GroupMemberController?param=groupmember&seq=" + <%=seq%> ;
}
function joingroup(){ //+++++ 모임가입버튼클릭시
		location.href = "GroupMemberController?param=joingroup&id=<%=id%>&name=<%=name%>&seq=<%=seq%>";
}
</script>

</body>
</html>