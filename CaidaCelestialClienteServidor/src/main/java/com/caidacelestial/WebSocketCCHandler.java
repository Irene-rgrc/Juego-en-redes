package com.caidacelestial;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebSocketCCHandler extends TextWebSocketHandler {
	boolean primero=false;
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(message.getPayload());
		JsonNode m = node.get("peticion");
		System.out.println("Message received: " + node.toString());
		
		switch(m.asText()){
		case "emparejar":
			if(!primero) {
				primero=true;
				String msg="Cassadie";
				session.sendMessage(new TextMessage(msg));
			}
			else {
				String msg="Seraphina";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		}
	}

}
