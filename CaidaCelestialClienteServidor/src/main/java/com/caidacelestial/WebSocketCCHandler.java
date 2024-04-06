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
		System.out.println("Message received: " + node.toString());
		
		switch(node.get("peticion").asText()){
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
		case "parar":
			if(primero) {
				String msg="Pausa1";
				session.sendMessage(new TextMessage(msg));
			}
			else {
				String msg="Pausa2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "continuar":
			if(primero) {
				String msg="Play1";
				session.sendMessage(new TextMessage(msg));
			}
			else {
				String msg="Play2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		}
	}

}
