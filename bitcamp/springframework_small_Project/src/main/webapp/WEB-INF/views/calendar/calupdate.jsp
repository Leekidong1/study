<%@page import="bit.com.a.dto.CalendarDto"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
CalendarDto dto = (CalendarDto)request.getAttribute("cal");
String year = dto.getRdate().substring(0, 4);
int month = Integer.parseInt(dto.getRdate().substring(4, 6));
int date = Integer.parseInt(dto.getRdate().substring(6, 8));
int hour = Integer.parseInt(dto.getRdate().substring(8, 10));
int min = Integer.parseInt(dto.getRdate().substring(10));
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div align="center">
	<form action="calupdateAf.do" method="get">
	<input type="hidden" name="id" value="<%=dto.getId() %>" readonly>
	<input type="hidden" name="seq" value="<%=dto.getSeq() %>" readonly>
		<table class="list_table" style="width: 85%" id="_list_table">
		<col width="200"><col width="500">
			<tr>
				<th>아이디</th>
				<td style="text-align: left">
					<%=dto.getId() %>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td style="text-align: left">
					<input type="text" size="60" name="title" value="<%=dto.getTitle()%>">
				</td>
			</tr>
			<tr>
				<th>일정</th>
				<td style="text-align: left">
					<select name="year">
					<%
						for(int i = Integer.parseInt(year) -5; i <= Integer.parseInt(year) + 5; i++){
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
						<option <%=(month+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
							<%=i %>
						</option>
					<%
						}
					%>
					</select>월
					
					<select name="date">
					<%
						Calendar cal = Calendar.getInstance();
						for(int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
					%>
						<option <%=(date+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
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
						<option <%=(hour+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
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
						<option <%=(min+"").equals(i + "")?"selected='selected'":"" %> value="<%=i %>">
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
					<textarea rows="20" cols="60" name="content"><%=dto.getContent() %></textarea>
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
						<input type="submit" value="일정수정완료">
					</span>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
$("select[name='date']").val("<%=date %>");

$(document).ready(function() {
	let obj = document.getElementById("important");
	obj.value = "<%=dto.getImportant() %>";
	obj.setAttribute("selected", "selected");
	
	
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