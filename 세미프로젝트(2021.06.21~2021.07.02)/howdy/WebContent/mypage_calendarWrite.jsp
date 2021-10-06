<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style>
body{
    font-size: 9pt;
    font-family: 맑은 고딕;
    color: #333333;
}
table{
    width:400px;
    border-collapse:collapse; 
}
th, td{
    border: 1px solid
     #cccccc;
    padding: 5px;
}
caption{
font-size: 14pt;
font-weight: bold;
margin-bottom: 5px;
}
.div1{
      width:400px;
      text-align: center;
      margin-top: 10px;
}

</style>
<body>

<%-- <%
MemberDto mem = (MemberDto)session.getAttribute("login");
if(mem == null){
%>  
	<script>
	alert("로그인 해 주십시오");
	location.href = "login.jsp";
	</script>	
<%
}
%> --%>



<%
Calendar cal = Calendar.getInstance();
int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH) + 1;
int tday = cal.get(Calendar.DATE);
int thour = cal.get(Calendar.HOUR_OF_DAY);
int tmin = cal.get(Calendar.MINUTE);

cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
%>

<h2>일정 추가</h2>

<form action="meet" method="post">
<input type="hidden" name="param" value="mypage_calendarWriteAf">


<table>
<caption>일정등록</caption>
     <tr>
        <th>카테고리</th>
        <td>
        <select id="category">
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
        <td><input type="text" name="c_location"></td>
     </tr>
     <tr>
        <th>제목</th>
        <td><input type="text" name="c_name"></td>
     </tr>
    <tr>
	<th>일정</th>
	<td>
		<select name="year">
		<%
			for(int i = tyear - 5;i <= tyear + 5; i++){
				%>	
				<option <%=year.equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
					<%=i %>
				</option>
				
				<%
			}		
		%>		
		</select>년	
		
		<select name="month">
		<%
			for(int i = 1;i <= 12; i++){
				%>	
				<option <%=month.equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
					<%=i %>
				</option>
				
				<%
			}		
		%>		
		</select>월
		
		<select name="day">
		<%
			for(int i = 1;i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
				%>	
				<option <%=day.equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
					<%=i %>
				</option>				
				<%
			}		
		%>		
		</select>일
		
		<select name="hour">
		<%
			for(int i = 1;i < 24; i++){
				%>	
				<option <%=(thour + "").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
					<%=i %>
				</option>				
				<%
			}		
		%>		
		</select>시
		
		<select name="min">
		<%
			for(int i = 0;i < 60; i++){
				%>	
				<option <%=(tmin + "").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
					<%=i %>
				</option>				
				<%
			}		
		%>		
		</select>분	
	</td>
</tr>

     <tr>
        <th>정원</th>
        <td><input type="text" name="max_mem"></td>
     </tr>
     <tr>
        <th>내용</th>
        <td><textarea rows="20" cols="60" name="c_content"></textarea></td>
     </tr>
</table>
<div class="div1">
  <button type="submit">저장</button>
</div>


</form>

</body>
</html>