<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int group_seq = Integer.parseInt(request.getParameter("seq"));
MemberDto dto = (MemberDto)session.getAttribute("login");
String id = "";

if(dto != null){
	id = dto.getId();
}
%>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
table{
	margin: 20px;
}
td{
	padding: 3px;
}
.sbox, .maxbox{
	border: 1px solid;
	border-radius:10%;
	padding: 5px;
	text-align: center;
}
.infobox{
	margin-left: 3px;
	font-size: 16px;
}
.content{
	padding: 8px;
}
.maxppl{
	text-align: center;
}
</style>
</head>
<body>

<div id="tables"></div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$.ajax({	// 비동기통신
		url:"./meet?param=detail_groupdesc_getMeetinglist",	// or url:"member?param=idcheck",
		type:"post",
		data:{ "group_seq":'<%=group_seq%>', "id":'<%=id %>'},
		datatype:"json",
		success:function(data){
		//	let str = JSON.stringify(data);	// 전체확인하기
		//	alert(str);
				
		
			 $.each(data.list, function(index, item) {
				let addMeetingMem = '<%=id %>';
				let group_seq = '<%=group_seq%>';
				let showlist =
					'<div class="card shadow-lg mb-3">'+
					'<table>'+
						'<col width="120px"><col width="50px"><col width="300px"><col width="100px"><col width="200px">' +
						'<tr>' +
							'<td rowspan="2">' +
								'<div class="sbox">' +
									item.category +
								'</div>' +
							'</td>' +
							'<td>' +
								'<img src="./img/cal.png" style="width:25px; height:25px">' +
							'</td>' +
							'<td>' +
								'<span class="infobox">' + item.sdate + '<br>~' + item.edate + '</span>' +
							'</td>' +
							'<td rowspan="3">' +
								'<div class="maxppl"><strong>정원</strong></div>' +
								'<div class="maxbox">' + item.max_mem + '</div>' +
							'</td>' +
							'<td rowspan="4" align="center">';
							for(i=0; i<data.Glist.length; i++){
								if(data.Glist[i].id == addMeetingMem && data.Glist[i].group_auth != 0){
								showlist +=	'<button class="btn btn-dark" value="' + addMeetingMem +' ' + item.meeting_seq + ' 참여하기 ' + group_seq + ' ' + item.max_mem + '" onclick="joinBtn(this.value)">참여하기</button><br><br>' +
											'<button class="btn btn-dark" value="' + addMeetingMem +' ' + item.meeting_seq + ' 참여취소 ' + group_seq + ' 0" onclick="joinBtn(this.value)">참여취소</button>';	
								}
							}
				showlist +=	'</td>' +
						'</tr>' +
						'<tr>' +
							'<td>' +
								'<img src="./img/loc.png" style="width:25px; height:25px">'	+				
							'</td>'+
							'<td>' +
								'<span class="infobox">' + item.location + '</span>' +	 
							'</td>'+
						'</tr>'+
						'<tr>'+
							'<td colspan="2">'+
								'<div class="content">제목 : ' + item.title + '</div>'+
							'</td>'+
						'</tr>'+
						'<tr>'+
							'<td colspan="4">'+
								'<div class="content">내용 : ' + item.content + '</div>'+
							'</td>'+
						'</tr>'+
					'</table>'+
				'</div>';
								
				$("#tables").append(showlist);
			}); 
			
		},
		error:function(){
			alert(error);
		}
	});
});

function joinBtn(value) {
		location.href = "meet?param=MeetingMember&value=" + value ;
}
</script>
</html>