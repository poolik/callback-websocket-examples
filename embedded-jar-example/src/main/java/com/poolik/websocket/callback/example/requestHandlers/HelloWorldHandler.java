package com.poolik.websocket.callback.example.requestHandlers;

import com.poolik.websocket.callback.RequestHandler;
import com.poolik.websocket.callback.WebsocketRequest;
import com.poolik.websocket.callback.WebsocketResponse;
import com.poolik.websocket.callback.request.RequestType;
import com.poolik.websocket.callback.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class HelloWorldHandler implements RequestHandler {

  private static final Logger log = LoggerFactory.getLogger(HelloWorldHandler.class);
  @Override
  public Pair<String, List<RequestType>> getRequestMappings() {
    return Pair.of("/helloworld", Arrays.asList(RequestType.POST));
  }

  @Override
  public WebsocketResponse handle(WebsocketRequest request) throws Exception {
    log.info("Recieved request: " + request);
    return new HelloWorldResponse(request.getRequestData());
  }

  class HelloWorldResponse implements WebsocketResponse {
    public final String greeting;

    HelloWorldResponse(String firstname) {
      this.greeting = "Hello " + firstname;
    }
  }
}