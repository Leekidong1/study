package bit.com.a.websocket;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocket extends TextWebSocketHandler {	//채팅서버영역
	
	private Map<String, WebSocketSession> userMap = new ConcurrentHashMap<String, WebSocketSession>();	// user들의 주소를 저장
	
	public WebSocket() {
		System.out.println("EchoHandler 생성됨 " + new Date()); 	// 네트워크 연결(웹서버완성)
	}

	// **클라이언트와 접속이 성공했을 때 호출
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("연결됨:" + session.getId() + " " + new Date());// HttpServlet용 Session과 다름으로 헷갈리지 말것
		
		userMap.put(session.getId(), session);	// 클리아언트 저장(login 개념)
	}
	// 클라이언트 접속 끊길때
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("연결 종료:" + session.getId() + " " + new Date());	// 클라이언트와 통신 끊겼을 경우
		
		userMap.remove(session.getId());	// 저장된 클라이언트 해제(logout 개념)
	}
	
	// **메세지(packet) 송(send)&수신(recv)하는 함수
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 수신 -> 송신 순서
		// 수신(recv) 대기상태: 언제 데이터 날라올지모름으로 수신을 한다.
		System.out.println("메시지 수신:" + message.getPayload() + " " + new Date());
		
		// 송신(send) - 접속되어 있는 모든 클라이언트에게 전송(특정한테 보낼때 구현해줘야할것)
		for(WebSocketSession s : userMap.values()) {
			s.sendMessage(message);
		}
	}
	
	// 예외발생, 에러발생
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println("예외발생:" + session.getId() + " " + new Date());
	}
	
	
}
