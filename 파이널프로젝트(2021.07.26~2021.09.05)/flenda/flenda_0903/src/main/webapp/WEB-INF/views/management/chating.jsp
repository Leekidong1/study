<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<fmt:requestEncoding value="utf-8"/>     

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="./css/chat/chatAdmin.css">

</head>
<body>

<!-- 세팅 부분 init -->
<input type="hidden" id="userId" value="${login.id}"> 
<input type="hidden" id="room" value="${room}">
   	<!-- 채팅방 -->
   	<form action="MoveChatRoom.do" method="post" id="moveChatForm">
   	<input type="hidden" name="roomName">
	</form>
   	<!-- 방만들기 form이용 --> 
	<form action="createChatRoom.do" method="post" id="createForm">
   		<input type="hidden" id="chkRoomName"> 
   	</form>
   	
   	
   	
 <div class="listArea">
   <div class="listHeader">방목록</div>
   <div class="btnArea">
   		<div class="btn-group">
				<input type="button" class="button" value="전체" id="allBtn">
				<input type="button" class="button" value="환불" id="rfnBtn">
				<input type="button" class="button" value="상품" id="gdsBtn">
				<input type="button" class="button" value="투어" id="turBtn">
				<input type="button" class="button" value="기타" id="rstBtn">
		</div>
	</div>
	<div id="getRoomList" class="roomListArea"></div>
</div>

<div class="chatWindow_admin" id="showhide">
	<div class="chatHead">
 		<div class="LuChatHeader_admin"></div>
 	 	<div class="endBtn"><input class="input" type="button" value="나가기" id="backBtn"></div> 
 	
 	</div>
   <div class="chatWindow__chat_admin" id="output">
  		<c:forEach items="${list}" var="dto">
			<c:if test="${dto.msg ne 'NULL'}">
				<c:if test="${dto.who eq 'user'}">
					<div class="chat__response"><p class="chat__response__text_admin">${dto.msg}</p></div>
				</c:if>
					<c:if test="${dto.who eq 'admin'}">
					<div class="chat__question"><p class="chat__question__text_admin">${dto.msg}</p></div>
				</c:if>
			</c:if>	
		</c:forEach>
  </div> 
    <div class="chatInputHolder">
<input type="text" class="chatWindow__question" name="chatInput" id="textID" placeholder="내용을 입력하세요..." autocomplete="off">
  <input type="button" class="chatSendBtn_admin" value="보내기" id="buttonMessage">
    </div>

</div>

<script type="text/javascript">
let id = '${login.id}';
let check="refurn";
let checkRoom = $("#room").val();
let cHeader =[];

$(document).ready(function() {
	if(checkRoom=='all'){
		//alert(checkRoom);	
		$("#showhide").hide();
	}else{
		$("#showhide").show();
		$(".LuChatHeader_admin").empty();
		cHeader=checkRoom.split("-");
		let c_Header;
		if(cHeader[0]=='refurn'){
			c_Header='환불문의'
		}else if(cHeader[0]=='goods'){
			c_Header='상품문의'
		}else if(cHeader[0]=='tour'){
			c_Header='투어문의'
		}else{
			c_Header='기타문의'
		}
		
		$(".LuChatHeader_admin").append(c_Header);
	}
	
   $("#textID").focus(); // 처음 접속시, 메세지 입력창에 focus 시킴

   if(id=='admin'){
      ws = new WebSocket("ws://localhost:8090/flenda/echo.do");
   }

   //서버로 메세지 보낼때
   ws.onopen = function() {
	//처음 접속 시에만 채팅방에 추가함(현재 방정보도 같이 출력)	   
      	$("#output").append("<p>채팅방에 입장했습니다.</p>");

      	$("#buttonMessage").click(function() {

         	let msg = $('#textID').val().trim(); //메시지
         	if(msg == ''){
         		alert('글을 작성해주세요');
         		$('#textID').focus();
         		return
         	}
         	let room = $("#room").val().trim(); //방이름(전체단톡방이면 all)

            	ws.send(msg+"!%/"+room+"!%/"+"admin");
            	$("#output").append('<div class="chat__question"><p class="chat__question__text">'+msg+'</p></div>');
            	$("#output").scrollTop(99999999); //글 입력 시 무조건 하단으로 보냄
            	$("#textID").val(""); //입력창 내용지우기
            	$("#textID").focus(); //입력창 포커스 획득
      	});
      	$("#textID").keypress(function(event) {
         	if(event.which == "13"){
            	event.preventDefault();
            	
            	let msg = $('#textID').val().trim(); //메시지
            	
            	if(msg == ''){
             		alert('글을 작성해주세요');
             		$('#textID').focus();
             		return
             	}
            	
             	let room = $("#room").val().trim(); //방이름(전체단톡방이면 all)

             	ws.send(msg+"!%/"+room+"!%/"+"admin");
                	$("#output").append('<div class="chat__question"><p class="chat__question__text">'+msg+'</p></div>');

                	$("#output").scrollTop(99999999); //글 입력 시 무조건 하단으로 보냄
                	$("#textID").val(""); //입력창 내용지우기
                	$("#textID").focus(); //입력창 포커스 획득
         	}
      	});
      	listSend("all");
      	
   	};

  //서버로 부터 받은 메세지 보내주기
   	ws.onmessage = function(message) {

    	//메세지 
      	let jsonData = JSON.parse(message.data);
 
      	if(jsonData.message !=null){
        	$("#output").append(jsonData.message+"<br>");
        	$("#output").scrollTop(99999999);
      	}
      	
      	//방 정보
      	
	};   
   	//닫힐때
   	ws.onclose = function(event) {};
});
</script>

<script type="text/javascript">

$("#allBtn").click(function() {
	listSend("all");
});
$("#rfnBtn").click(function() {
	listSend("refurn");
});
$("#gdsBtn").click(function() {
	listSend("goods");
});
$("#turBtn").click(function() {
	listSend("tour");
});
$("#rstBtn").click(function() {
	listSend("rest");
});
function listSend(check){
	
	$.ajax({
		url:"searchRoom.do",
		type: "post",
		data:{category:check},
		success:function(msg){

 			let json = JSON.parse(msg);
	      	if(json.room !=null){
				let roomSplit = json.room.split(',');
				let str="";
				for( i=1; i<roomSplit.length; i++){
					let spl = roomSplit[i].split("/");
					
					let title = [];
					let user = [];
					title = spl[0].split("-");
					user = spl[0].split("-");
					if(title[0]=='refurn'){
						title = '환불문의';
					}
					if(title[0]=='goods'){
						title = '상품문의';
					}
					if(title[0]=='tour'){
						title = '투어문의';
					}
					if(title[0]=='rest'){
						title = '기타문의';
					}
				
					str += "<div class='detaillist' "+
					"onclick=\"moveRoom('"+spl[0]+"','"+spl[1]+"','"+spl[2]+"')\"> "
					+title+"("+user[1]+"님)</div>";
				} 
				
				$("#getRoomList").html(str);
	      	}
		}, 
		error:function(){
			alert("error");
		}
	});
}
</script>

<!-- 방이동 처리함수 -->
<script type="text/javascript">

$("#backBtn").click(function() {
	location.href="chat.do";
});

function moveRoom(room,remaincount,totalcount) {
		let currentRoom = $("#room").val(); //현재자신이있는방
		if(room == currentRoom){
		   alert("현재 선택하신 방에 있습니다.");
		}
		else{
			if(remaincount<totalcount){
			         $("[name='roomName']").val(room);               
			         $("#moveChatForm").attr("target","_self").submit();   
			}
			else{
			   alert("인원 수가 가득찼습니다.");
			}
		}
};
</script>
</body>
</html>