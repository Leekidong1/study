<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<table class="list_table" style="width: 85%">
	<col width="200px"><col width="500px">
	<tr>
		<th>대화명</th>
		<td style="text-align: left;">
			<input type="text" id="name">
			<input type="button" id="enterBtn" value="입장" onclick="connect()">
			<input type="button" id="exitBtn" value="나가기" onclick="disconnect()">
		</td>
	</tr>
	<tr>
		<th>아이디</th>
		<td style="text-align: left;">
			<input type="text" id="id" value="${login.id}" readonly="readonly">
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<textarea rows="10" cols="70" id="MessageArea"></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="text" id="message" size="50">
			<input type="button" id="sendBtn" value="전송" onclick="send()">
		</td>
	</tr>
</table>

<script>
var wsocket;

function connect() {
	if(wsocket != undefined && wsocket.readyState != WebSocket.CLOSED){
		alert('이미 입장하셨습니다');
		return;
	}
	
	// websocket 생성
//	if($("#name").val() == "aaa"){
//		wsocket = new WebSocket("ws://localhost:8090/springSample/echo.do");	// 프로젝트이름 들어가기
	//	wsocket = new WebSocket("ws://192.168.35.79:8090/springSample/echo.do");	// ip주소 보이는 이 방법으로 보통쓰임
		
//	}else{
		wsocket = new WebSocket("ws://192.168.35.79:8090/springSample/echo.do"); 
//	}
	
	wsocket.onopen = onOpen;
	wsocket.onmessage = onMessage;
	wsocket.close = onClose;
}

function disconnect() {
	wsocket.close();
	location.href = "chating.do";
}

// 아래 3개 함수는 네트워크 사용시에 지정해야할 것
function onOpen(evt) {	// websocket에 연결되었을 때, 실행됨
	appendMessage("서버에 연결되었습니다");
}

function onMessage(evt) {	// 수신(recv)이 되었을 때, 실행됨
	let data = evt.data;
	if(data.substring(0, 4) == "msg:"){
		appendMessage(data.substring(4));
	}
}

function onClose(evt) {	// websocket에 연결이 끊겼을 때, 실행됨
	appendMessage("서버연결이 끊겼습니다");
}
// 메세지(packet) 송신(보내기)
function send() {
	let id = $("#name").val();
	let msg = $("#message").val();
	
	wsocket.send("msg:" + id + ":" + msg);	// msg:aaa:안녕
	$("#message").val("");
}
// textarea에 문자열을 추가함수
function appendMessage(msg) {
	$("#MessageArea").append(msg + "\n");
	//스크롤을 위로 이동시킨다
	const top = $("#MessageArea").prop("scrollHeight");	//const는 상수(final)
	$("#MessageArea").scrollTop(top);
}
</script>