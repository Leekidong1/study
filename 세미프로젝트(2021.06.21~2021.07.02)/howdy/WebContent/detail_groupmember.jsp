<%@page import="dto.MemberDto"%>
<%@page import="dao.GroupMemberDao"%>
<%@page import="java.util.List"%>
<%@page import="dto.GroupMemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
int seq = Integer.parseInt(request.getParameter("seq")); //+++++ 시퀀스, dao, list 호출 스크립트릿추가
System.out.println("seq:"+seq);
GroupMemberDao dao = GroupMemberDao.getInstance(); 
List<MemberDto> list = dao.getGroupMemberList(seq);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="card text-center shadow-lg mb-3">
	<table style="border: 3px solid white; ">
	<col width="100"><col width="100"><col width="600">
	<%
	for(int i = 0; i < list.size(); i++){ //+++++ for문시작
		MemberDto gmd = list.get(i);
		System.out.println("gmd:"+gmd.getId());
	%>
		<tr>
			<td height="50" align="center" class="table-info"> <!-- 0630 male/female 분기추가 -->
				<%if (gmd.getGender().equals("male")) {%>
					<img src = "./img/male.png" width="50px" height="50px">
				<%}else{ %>
					<img src = "./img/female.png" width="50px" height="50px">
				<% } %>
			</td>
			<td align="center" scope="row" class="table-info">
				<%=gmd.getName() %>
			</td>
				<%  
				if(gmd.getGroup_auth()== 1) {
				%>
			<td align="center" class="table-success">모임원</td>
				<%
				}else if(gmd.getGroup_auth()== 2) {
				%>	
			<td align="center" class="table-success">부모임장</td>
				<%
				}else if(gmd.getGroup_auth()== 3) {
				%>
			<td align="center" class="table-success">모임장</td>	
				<%
				}else{
				%>
			<td align="center" class="table-warning">가입 대기중인 회원</td>	
				<%
				}
	}
				%>
		</tr>
	</table>
</div>
</body>
</html>