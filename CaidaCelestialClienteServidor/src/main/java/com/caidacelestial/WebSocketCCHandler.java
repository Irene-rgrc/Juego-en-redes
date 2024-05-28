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
	
	//Controles Seraphina
	
	boolean up1=false;
	boolean left1=false;
	boolean right1=false;
	
	boolean up2=false;
	boolean left2=false;
	boolean right2=false;

	//controles Cassadie
	boolean a1=false;
	boolean d1=false;
	boolean w1=false;

	boolean a2=false;
	boolean d2=false;
	boolean w2=false;
	
	//Habilidades Especiales
	
	boolean salto1 = false;
	boolean salto2 = false;
	
	boolean sprint1 = false;
	boolean sprint2 = false;
	
	boolean h1 = false;
	boolean h2 = false;
	
	// FINALES
	boolean finalBueno = false;
	boolean finalMalo = false;

	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(message.getPayload());
		//System.out.println("Message received: " + node.toString());
		
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
			if(jugador.equals("player1")) {
				cassidieFinal = true; 
				System.out.println("cassidieFinal");
				session.sendMessage(new TextMessage("cassidieElige"));
			} 
			else if(jugador.equals("player2")) {
				seraphineFinal = true;
				System.out.println("seraphineFinal");
				session.sendMessage(new TextMessage("seraphinaElige"));
			}
			break;
			
		case "getFinal":
			if(cassidieFinal == true) {
				session.sendMessage(new TextMessage("cassidieElige"));
			} else if (seraphineFinal == true) {
				session.sendMessage(new TextMessage("seraphinaElige"));
			}
			break;
		
		case "comprobarExisteFinal":
			if(cassidieFinal == true || seraphineFinal == true) {
				// SI HAY FINAL ASI QUE AHORA ES VER SI ES BUENO O MALO
				session.sendMessage(new TextMessage("finalElegiendo"));
			}
			break;
		
		case "finalBueno":
			finalBueno = true;
			break;
		case "finalMalo":
			finalMalo = true;
			break;
		
		case "tipoFinal":
			if(finalBueno == true) {
				session.sendMessage(new TextMessage("esBueno"));
			}
			if (finalMalo == true) {
				session.sendMessage(new TextMessage("esMalo"));
			}
			break;
			
			
		case "pulsarJugadorLeft":
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
			
		case "soltarJugadorLeft":
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
			
		case "pulsadoJugadorLeft":
			
			if(left1 == true) {
				String msg="pulsadoLeft1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(left2 == true) {
				String msg="pulsadoLeft2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "seguirJugadorLeft":
			if(left1 == false) {
				String msg="SoltadoLeft1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(left2 == false) {
				String msg="soltadoLeft2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "pulsarJugadorRight":
			if(node.get("player").asText().equals("player2")) {
				right1 = true;
				String msg="pulsadoRight1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				right2 = true;
				String msg="pulsadoRight2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "soltarJugadorRight":
			if(node.get("player").asText().equals("player2")) {
				right1=false;
				String msg="soltadoRight1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				right2=false;
				String msg="soltadoRight2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "pulsadoJugadorRight":
			
			if(right1 == true) {
				String msg="pulsadoRight1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(right2 == true) {
				String msg="pulsadoRight2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "seguirJugadorRight":
			if(right1 == false) {
				String msg="SoltadoRight1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(right2 == false) {
				String msg="soltadoRight2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "pulsarJugadorUp":
			if(node.get("player").asText().equals("player2")) {
				up1 = true;
				String msg="pulsadoUp1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				up2 = true;
				String msg="pulsadoUp2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "soltarJugadorUp":
			if(node.get("player").asText().equals("player2")) {
				up1=false;
				String msg="soltadoUp1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				up2=false;
				String msg="soltadoUp2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "pulsadoJugadorUp":
			
			if(up1 == true) {
				String msg="pulsadoUp1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(up2 == true) {
				String msg="pulsadoUp2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "seguirJugadorUp":
			if(up1 == false) {
				String msg="SoltadoUp1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(up2 == false) {
				String msg="soltadoUp2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "pulsarJugadorA":
			if(node.get("player").asText().equals("player2")) {
				a1 = true;
				String msg="pulsadoA1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				a2 = true;
				String msg="pulsadoA2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "soltarJugadorA":
			if(node.get("player").asText().equals("player2")) {
				a1=false;
				String msg="soltadoA1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				a2=false;
				String msg="soltadoA2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "pulsadoJugadorA":
			
			if(a1 == true) {
				String msg="pulsadoA1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(a2 == true) {
				String msg="pulsadoA2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "seguirJugadorA":
			if(a1 == false) {
				String msg="SoltadoA1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(a2 == false) {
				String msg="soltadoA2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "pulsarJugadorD":
			if(node.get("player").asText().equals("player2")) {
				d1 = true;
				String msg="pulsadoD1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				d2 = true;
				String msg="pulsadoD2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "soltarJugadorD":
			if(node.get("player").asText().equals("player2")) {
				d1=false;
				String msg="soltadoD1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				d2=false;
				String msg="soltadoD2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "pulsadoJugadorD":
			
			if(d1 == true) {
				String msg="pulsadoD1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(d2 == true) {
				String msg="pulsadoD2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "seguirJugadorD":
			if(d1 == false) {
				String msg="SoltadoD1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(d2 == false) {
				String msg="soltadoD2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "pulsarJugadorW":
			if(node.get("player").asText().equals("player2")) {
				w1 = true;
				String msg="pulsadoW1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				w2 = true;
				String msg="pulsadoW2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "soltarJugadorW":
			if(node.get("player").asText().equals("player2")) {
				w1=false;
				String msg="soltadoW1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				w2=false;
				String msg="soltadoW2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "pulsadoJugadorW":
			
			if(w1 == true) {
				String msg="pulsadoW1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(w2 == true) {
				String msg="pulsadoW2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "seguirJugadorW":
			if(w1 == false) {
				String msg="SoltadoW1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(w2 == false) {
				String msg="soltadoW2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "pulsarJugadorSalto":
			if(node.get("player").asText().equals("player2")) {
				salto1 = true;
				String msg="pulsadoSalto1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				salto2 = true;
				String msg="pulsadoSalto2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "soltarJugadorSalto":
			if(node.get("player").asText().equals("player2")) {
				salto1=false;
				String msg="soltadoSalto1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				salto2=false;
				String msg="soltadoSalto2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "pulsadoJugadorSalto":
			
			if(salto1 == true) {
				String msg="pulsadoSalto1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(salto2 == true) {
				String msg="pulsadoSalto2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "seguirJugadorSalto":
			if(salto1 == false) {
				String msg="SoltadoSalto1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(salto2 == false) {
				String msg="soltadoSalto2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "pulsarJugadorSprint":
			if(node.get("player").asText().equals("player2")) {
				sprint1 = true;
				String msg="pulsadoSprint1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				sprint2 = true;
				String msg="pulsadoSprint2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "soltarJugadorSprint":
			if(node.get("player").asText().equals("player2")) {
				sprint1=false;
				String msg="soltadoSprint1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				sprint2=false;
				String msg="soltadoSprint2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "pulsadoJugadorSprint":
			
			if(sprint1 == true) {
				String msg="pulsadoSprint1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(sprint2 == true) {
				String msg="pulsadoSprint2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "seguirJugadorSprint":
			if(sprint1 == false) {
				String msg="SoltadoSprint1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(sprint2 == false) {
				String msg="soltadoSprint2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "pulsarJugadorH":
			if(node.get("player").asText().equals("player2")) {
				h1 = true;
				String msg="pulsadoH1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				h2 = true;
				String msg="pulsadoH2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "soltarJugadorH":
			if(node.get("player").asText().equals("player2")) {
				h1=false;
				String msg="soltadoH1";
				session.sendMessage(new TextMessage(msg));
			}
			if(node.get("player").asText().equals("player1")) {
				h2=false;
				String msg="soltadoH2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			
		case "pulsadoJugadorH":
			
			if(h1 == true) {
				String msg="pulsadoH1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(h2 == true) {
				String msg="pulsadoH2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
		case "seguirJugadorH":
			if(h1 == false) {
				String msg="SoltadoH1";
				session.sendMessage(new TextMessage(msg));
			}
			
			if(h2 == false) {
				String msg="soltadoH2";
				session.sendMessage(new TextMessage(msg));
			}
			break;
			}	
		}
		
	}
	

