package com.poolik.websocket.callback.example;

import com.poolik.websocket.callback.WebsocketRequestHandler;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket/")
public class WebsocketEndpoint {

  @OnMessage
  public void onWebSocketText(final Session session, String message) {
    WebsocketRequestHandler.handleRequest(session, message);
  }
}
