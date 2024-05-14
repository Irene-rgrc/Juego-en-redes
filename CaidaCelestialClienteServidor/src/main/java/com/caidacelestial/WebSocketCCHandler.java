package com.caidacelestial;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebSocketCCHandler extends TextWebSocketHandler {
	boolean primero=false;
	boolean segundo=false;
	// Pausa
	boolean pausa1=false;
	boolean pausa2=false;
	// JUGADORES
	boolean p1ready = false;
	boolean p2ready = false;
	
	
	boolean seraphineFinal = false;
	boolean cassidieFinal = false;
	
	//Controles j1
	
	boolean up1=false;
	boolean down1=false;
	boolean left1=false;
	boolean right1=false;
	
	//Controles j2
	boolean up2=false;
	boolean down2=false;
	boolean left2=false;
	boolean right2=false;
	
	
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
				System.out.println(msg);
			}
			else if(!segundo){
				segundo=true;
				String msg="Seraphina";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "parar":
			if(node.get("player").asText().equals("player1")) {
				pausa1 = true;
				String msg="Pausa1";
				session.sendMessage(new TextMessage(msg));
			}
			else if(node.get("player").asText().equals("player2")) {
				pausa2 = true;
				String msg="Pausa2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "pausa":
			
			if(pausa1 == true) {
				String msg="Pausa1";
				session.sendMessage(new TextMessage(msg));
			}
			if(pausa2 == true) {
				String msg="Pausa2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "continuar":
			if(node.get("player").asText().equals("player1")) {
				pausa1=false;
				String msg="Play1";
				session.sendMessage(new TextMessage(msg));
			}
			else if(node.get("player").asText().equals("player2")) {
				pausa2=false;
				String msg="Play2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "play":
			if(pausa1 == false) {
				String msg="Play1";
				session.sendMessage(new TextMessage(msg));
			}
			if(pausa2 == false) {
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
			if(jugador.equals("player1")) cassidieFinal = true; // A LO MEJOR FALTA CASE DE ESTO
			else if(jugador.equals("player2")) seraphineFinal = true;
			break;
			
		case "elegirFinal":
			if (cassidieFinal) session.sendMessage(new TextMessage("cassidieElige"));
			if (seraphineFinal) session.sendMessage(new TextMessage("seraphinaElige"));
			break;
			
		case "controlesj1":
			if(up1 == true) {
				String msg="Up1";
				session.sendMessage(new TextMessage(msg));
			}
			if(down1 == true) {
				String msg="Down1";
				session.sendMessage(new TextMessage(msg));
			}
			if(left1 == true) {
				String msg="Left1";
				session.sendMessage(new TextMessage(msg));
			}
			if(right1 == true) {
				String msg="Right1";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "controlesSoltarj1":
			if(node.get("player").asText().equals("player1")) {
		        if(node.get("tecla").asText().equals("up")) {
		            up1 = false;
		        } else if(node.get("tecla").asText().equals("down")) {
		            down1 = false;
		        } else if(node.get("tecla").asText().equals("left")) {
		            left1 = false;
		        } else if(node.get("tecla").asText().equals("right")) {
		            right1 = false;
		        }
		     String msg = "Play1; up1=" + up1 + ", down1=" + down1 + ", left1=" + left1 + ", right1=" + right1;
		     session.sendMessage(new TextMessage(msg));
			}
			break;
			
			
		case "pulsarJugador":
			if(node.get("player").asText().equals("player2")) {
				left1 = true;
				String msg="pulsadoLeft1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				left2 = true;
				String msg="pulsadoLeft2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "soltarJugador":
			if(node.get("player").asText().equals("player2")) {
				left1=false;
				String msg="soltadoLeft1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				left2=false;
				String msg="soltadoLeft2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "pulsadoJugador":
			
			if(left1 == true) {
				String msg="pulsadoLeft1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(left2 == true) {
				String msg="pulsadoLeft2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "seguirJugador":
			if(left1 == false) {
				String msg="SoltadoLeft1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(left2 == false) {
				String msg="soltadoLeft2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			}	
		}
		
	}
	

