package com.caidacelestial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@SpringBootApplication
@EnableWebSocket
public class CaidaCelestialClienteServidorApplication implements WebSocketConfigurer{
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
			registry.addHandler(handlerCC(), "/caidacelestial")
			.setAllowedOrigins("*");
	}

	@Bean
	public WebSocketCCHandler handlerCC() {
		return new WebSocketCCHandler();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CaidaCelestialClienteServidorApplication.class, args);
	}
}
