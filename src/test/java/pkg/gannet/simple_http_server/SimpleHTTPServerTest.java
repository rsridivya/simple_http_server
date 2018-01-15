package pkg.gannet.simple_http_server;

import org.junit.Test;

public class SimpleHTTPServerTest {
	
	@Test
	public void testMain() throws Exception{
		SimpleHTTPServer server = new SimpleHTTPServer();
		System.out.println("main");
	    String[] args = null;
	    server.main(args);
	}
}