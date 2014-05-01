package com.poolik.websocket.callback.example.requestHandlers;

import com.poolik.websocket.callback.WebSocketRequest;
import com.poolik.websocket.callback.WebSocketRequestHandler;
import com.poolik.websocket.callback.WebSocketResponse;
import com.poolik.websocket.callback.request.RequestType;
import com.poolik.websocket.callback.response.StringResponse;
import com.poolik.websocket.callback.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class HelloWorldHandler implements WebSocketRequestHandler {

  private static final Logger log = LoggerFactory.getLogger(HelloWorldHandler.class);
  @Override
  public Pair<String, List<RequestType>> getRequestMappings() {
    return Pair.of("/helloworld", Arrays.asList(RequestType.POST));
  }

  @Override
  public WebSocketResponse handle(WebSocketRequest request) throws Exception {
    log.info("Recieved request: " + request);
    return new StringResponse("Hello " + request.getRequestBody());
  }
}