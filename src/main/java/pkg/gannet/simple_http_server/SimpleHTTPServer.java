package pkg.gannet.simple_http_server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/*
 * a simple static http server
*/
public class SimpleHTTPServer {

  public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(8004), 0);
    server.createContext("/", new MyHandler());
    server.setExecutor(null); // creates a default executor
    server.start();
  }
  }