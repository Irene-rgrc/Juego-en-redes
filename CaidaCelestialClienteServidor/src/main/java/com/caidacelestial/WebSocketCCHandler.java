package com.caidacelestial;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebSocketCCHandler extends TextWebSocketHandler {
	boolean primero=false;
	boolean segundo=false;
	// JUGADORES
	boolean p1ready = false;
	boolean p2ready = false;
	
	
	boolean seraphineFinal = false;
	boolean cassidieFinal = false;
	
	
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
			else if(!segundo){
				segundo=true;
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
		case "playerReady":
			String player = node.get("player").asText();
			if(player.equals("player1")) p1ready = true;
			else if(player.equals("player2")) p2ready = true;
			break;
		case "checkPlayersReady":
			if(p1ready && p2ready) session.sendMessage(new TextMessage("listos"));
			break;
		case "playerPuertas":
			String jugador = node.get("player").asText();
			if(jugador.equals("player1")) cassidieFinal = true;
			else if(jugador.equals("player2")) seraphineFinal = true;
			break;
		case "elegirFinal":
			if (cassidieFinal) session.sendMessage(new TextMessage("cassidieElige"));
			if (seraphineFinal) session.sendMessage(new TextMessage("seraphinaElige"));
			break;
		}
	}

}
