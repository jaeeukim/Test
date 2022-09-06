package com.myhome.web.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.myhome.web.emp.model.EmpDTO;

public class ChattingCS extends TextWebSocketHandler{
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private Map<String, WebSocketSession> sessionMap = new HashMap<String, WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		Map<String, Object> map = session.getAttributes();
		String name = (String)map.get("HTTP.SESSION.ID");
		
		if(map.get("loginData") != null) {
			name = ((EmpDTO)map.get("loginData")).getEmpName();
		}
		
		for(Entry<String, WebSocketSession> entry : sessionMap.entrySet()) {
			entry.getValue().sendMessage(new TextMessage("<p>" + name + " 님이 접속하였습니다.</p>"));
		}
		
		sessionMap.put(name, session);
		
		super.afterConnectionEstablished(session);
		
	}
	
	// 서버가 수신하면 동작
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		super.handleTextMessage(session, message);
		
		Map<String, Object> map = session.getAttributes();
		String name = (String)map.get("HTTP.SESSION.ID");
		
		if(map.get("loginData") != null) {
			name = ((EmpDTO)map.get("loginData")).getEmpName();
		}
		
		// 전체 송신
		for(Entry<String, WebSocketSession> entry : sessionMap.entrySet()) {
			WebSocketSession ws = entry.getValue();
			ws.sendMessage(new TextMessage("<p>" + name + " 님이 보낸 메시지<br>" 
											+ message.getPayload() + "</p>"));
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		Map<String, Object> map = session.getAttributes();
		String name = (String)map.get("HTTP.SESSION.ID");
		
		if(map.get("loginData") != null) {
			name = ((EmpDTO)map.get("loginData")).getEmpName();
		}
		
		for(Entry<String, WebSocketSession> entry : sessionMap.entrySet()) {
			WebSocketSession ws = entry.getValue();
			ws.sendMessage(new TextMessage("<p>" + name + " 님이 접속하였습니다.</p>"));
		}
		
		sessionMap.remove(name);
		
		super.afterConnectionClosed(session, status);
	}
	
	
	
}
