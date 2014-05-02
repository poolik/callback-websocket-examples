import com.poolik.callback.websocket.comparision.WebSocketEndpoint;
import com.poolik.callback.websocket.comparision.servlets.HelloServlet;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Launcher {

  private static final Logger log = LoggerFactory.getLogger(Launcher.class);
  public static void main(String[] args) throws Exception {
    startJetty();
    log.info("Started jetty!");
  }

  private static void startJetty() throws Exception {
    Server server = new Server();

    HttpConfiguration http_config = new HttpConfiguration();
    http_config.setOutputBufferSize(32768);

    ServerConnector http = new ServerConnector(server,new HttpConnectionFactory(http_config));
    http.setPort(8080);
    http.setIdleTimeout(600000); //10min
    server.addConnector(http);

    WebAppContext context = new WebAppContext();
    String webDir = Launcher.class.getClassLoader().getResource("webapp").toExternalForm();
    context.setResourceBase(webDir);
    context.setContextPath("/websocket");
    context.addServlet(HelloServlet.class, "/helloViaServlet");
    server.setHandler(context);

    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
      // fix for Windows, so Jetty doesn't lock files
      context.getInitParams().put("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
    }

    ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(context);

    // Add WebSocket endpoint to javax.websocket layer
    wscontainer.addEndpoint(WebSocketEndpoint.class);
    server.start();
  }
}
