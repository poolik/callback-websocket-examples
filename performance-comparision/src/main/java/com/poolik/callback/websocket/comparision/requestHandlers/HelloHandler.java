package com.poolik.callback.websocket.comparision.requestHandlers;

import com.poolik.websocket.callback.WebSocketRequest;
import com.poolik.websocket.callback.WebSocketRequestHandler;
import com.poolik.websocket.callback.WebSocketResponse;
import com.poolik.websocket.callback.request.RequestType;
import com.poolik.websocket.callback.response.StringResponse;
import com.poolik.websocket.callback.util.Pair;

import java.util.Arrays;
import java.util.List;

public class HelloHandler implements WebSocketRequestHandler {
  @Override
  public Pair<String, List<RequestType>> getRequestMappings() {
    return Pair.of("/helloViaWebSocket", Arrays.asList(RequestType.POST));
  }

  @Override
  public WebSocketResponse handle(WebSocketRequest request) throws Exception {
    return new StringResponse("Hello " + request.getRequestBody());
  }
}
