package com.poolik.websocket.callback.example;

import com.poolik.websocket.callback.WebSocketRequestMarshaller;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket/")
public class WebsocketEndpoint {
  private static final WebSocketRequestMarshaller marshaller = new WebSocketRequestMarshaller();

  @OnMessage
  public void onWebSocketText(final Session session, String message) {
    marshaller.handleRequest(session, message);
  }
}
