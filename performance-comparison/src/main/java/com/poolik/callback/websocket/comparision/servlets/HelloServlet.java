package com.poolik.callback.websocket.comparision.servlets;

import com.google.gson.Gson;
import com.poolik.websocket.callback.response.StringResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
  private static Gson gson = new Gson();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    resp.setStatus(HttpServletResponse.SC_OK);
    resp.getWriter().println(gson.toJson(new StringResponse("Hello " + getRequestBody(req))));
  }

  private String getRequestBody(HttpServletRequest req) throws IOException {
    BufferedReader reader = req.getReader();
    StringBuilder inputStringBuilder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      inputStringBuilder.append(line);
    }
    return inputStringBuilder.toString();
  }
}