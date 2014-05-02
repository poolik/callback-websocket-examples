package com.poolik.callback.websocket.comparision;

import com.poolik.websocket.callback.WebSocketRequestMarshaller;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket/")
public class WebSocketEndpoint {
  private static final WebSocketRequestMarshaller marshaller = new WebSocketRequestMarshaller();

  @OnMessage
  public void onWebSocketText(final Session session, String message) {
    marshaller.handleRequest(session, message);
  }
}