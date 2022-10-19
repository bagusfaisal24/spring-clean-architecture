package com.clean.architecture.websocket;

import com.clean.architecture.model.ProductModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ServerWebSocketHandler extends TextWebSocketHandler implements SubProtocolCapable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerWebSocketHandler.class);

    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("web socket connection opened");
        sessions.add(session);

        TextMessage textMessage = new TextMessage("web socket connection opened");
        LOGGER.info("server send : {}", textMessage);
        session.sendMessage(textMessage);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOGGER.info("web connection closed status {}", status);
        sessions.remove(session);

        super.afterConnectionClosed(session, status);
    }

    @Scheduled(fixedRate = 60000)
    public void sendPeriodicMessage() throws IOException {
        for (WebSocketSession session : sessions){
            if (session.isOpen()){
//                String message = String.format("periodic send event message date : %s", LocalDateTime.now());
                String data = jsonString();
                LOGGER.info("Server sends: {}", data);
                session.sendMessage(new TextMessage(data));
            }
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String fromClient =message.getPayload();
        LOGGER.info("server received : {} ", fromClient);

        String response = String.format("response from server to '%s'", HtmlUtils.htmlEscape(fromClient));
        LOGGER.info("Server sends: {}", response);
        session.sendMessage(new TextMessage(response));
        super.handleTextMessage(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOGGER.error("error transport web socket : {}", exception.getMessage());
        super.handleTransportError(session, exception);
    }

    @Override
    public List<String> getSubProtocols() {
        return Collections.singletonList("subprotocol.testwebsocket");
    }

    private String jsonString() throws JsonProcessingException {
        ProductModel product = new ProductModel();
        product.setProductName("TV");
        product.setPrice(1000000d);
        return new ObjectMapper().writeValueAsString(product);
    }
}
