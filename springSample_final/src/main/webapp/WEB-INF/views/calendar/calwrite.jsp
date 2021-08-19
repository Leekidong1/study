<%@page import="bit.com.a.dto.MemberDto"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%
// 로그인이 되어있는지 확인하기 
MemberDto mem = (MemberDto)session.getAttribute("login");
if(mem == null){
%>
	<script type="text/javascript">
		alert('로그인 해주세요');
		location.href = "login.do";
	</script>
<%
}

String year = (int)request.getAttribute("year") + "" ;
String month = (int)request.getAttribute("month") + "";
String date = (int)request.getAttribute("date") + "";
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<%
Calendar cal = Calendar.getInstance();
//오늘 날짜시간대 불러옴
int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH)+1;
int tdate = cal.get(Calendar.DATE);
int thour = cal.get(Calendar.HOUR);
int tmin = cal.get(Calendar.MINUTE);
// 날짜시간대 셋팅한다
cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
%>

<div align="center">
	<form action="calwriteAf.do" method="post">
		<table class="list_table" style="width: 85%" id="_list_table">
		<col width="200"><col width="500">
			<tr>
				<th>아이디</th>
				<td style="text-align: left">
					<%=mem.getId() %>
					<input type="hidden" name="id" value="<%=mem.getId() %>">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td style="text-align: left">
					<input type="text" size="60" name="title">
				</td>
			</tr>
			<tr>
				<th>일정</th>
				<td style="text-align: left">
					<select name="year">
					<%
						for(int i = tyear -5; i <= tyear + 5; i++){
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
						for(int i = 1; i <= 12; i++){
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
						for(int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
					%>
						<option <%=date.equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
							<%=i %>
						</option>
					<%
						}
					%>
					</select>일
					
					<select name="hour">
					<%
						for(int i = 1; i < 24; i++){
					%>
						<option <%=(thour+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
							<%=i %>
						</option>
					<%
						}
					%>
					</select>시
					
					<select name="min">
					<%
						for(int i = 0; i < 60; i++){
					%>
						<option <%=(tmin+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
							<%=i %>
						</option>
					<%
						}
					%>
					</select>분
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td style="text-align: left">
					<textarea rows="20" cols="60" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<th>중요도</th>
				<td style="text-align: left">
				<select name="important" id="important" style="background-color: green; color: white;">
					<option style="background-color: red; color: white;" value="verymuch">가장중요</option>
					<option style="background-color: blue; color: white;" value="very">중요</option>
					<option style="background-color: green; color: white;" value="normal" selected="selected">보통</option>
				</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<span class="button blue">
						<input type="submit" value="일정추가">
					</span>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
$("select[name='day']").val("<%=date %>");

$(document).ready(function() {
	
	$("select[name='month']").change(setMonth);
	$("select[name='important']").change(setColor);
});

function setMonth() {
	let year = $("select[name='year']").val();	//윤달까지 체크하기위해서 년도 걸림
	let month = $("select[name='month']").val();
	
	let lastday = (new Date(year, month, 0)).getDate();
	
	//날짜 적용
	let str = '';
	for(i = 1; i <= lastday; i++){
		str += "<option value='" + i + "'>" + i + "</option>";
	}
	
	$("select[name='date']").html(str);
}
function setColor() {
	let val = $("select[name='important']").val();
	if(val == 'verymuch'){
		$('#important').css("background-color", "red");
	}else if(val == 'very'){
		$('#important').css("background-color", "blue");
	}else if(val == 'normal'){
		$('#important').css("background-color", "green");
	}
	$('#important').css("color", "white");
}
</script>
</body>
</html>