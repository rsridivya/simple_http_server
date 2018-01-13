package pkg.gannet.simple_http_server;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.mock;

import com.sun.net.httpserver.HttpExchange;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(MyHandler.class)
public class MyHanlderTest {
	
	@Test
	public void checkProcessParametersSetFinalStringifHello() throws IOException {
		MyHandler server = new MyHandler();
		server.setFinalString("");
		Map<String,String> queryParams = new HashMap();
		queryParams.put("uppercase", "true");
		queryParams.put("reverse", "true");
		String inputString = "hello";
		server.processParameters(queryParams,inputString);
		Assert.assertEquals("Hello\nConverting hello to Uppercase: HELLO\nReversing hello :olleh\n",server.getFinalString());
	}
	
	@Test
	public void checkProcessParametersSetFinalStringifWorld() throws IOException {
		MyHandler server = new MyHandler();
		server.setFinalString("");
		Map<String,String> queryParams = new HashMap();
		queryParams.put("uppercase", "true");
		queryParams.put("reverse", "true");
		String inputString = "world";
		server.processParameters(queryParams,inputString);
		Assert.assertEquals("World\nConverting world to Uppercase: WORLD\nReversing world :dlrow\n",server.getFinalString());
	}
	
	@Test
	public void checkProcessParametersSetFinalStringifNull() throws IOException {
		MyHandler server = new MyHandler();
		server.setFinalString("");
		Map<String,String> queryParams = new HashMap();
		String inputString = "";
		server.processParameters(queryParams,inputString);
		Assert.assertEquals("Hello World\nNo Query Parameters Provided",server.getFinalString());
	}

	@Test
	public void checkParseQueryStringReturnCorrectMap(){
		MyHandler server = new MyHandler();
		server.setFinalString("");
		Assert.assertEquals("{uppercase=true, reverse=false}",server.parseQueryString("uppercase=true&reverse=false").toString());
	}
	
	@Test
	public void checkParseQueryStringReturnCorrectMapIfParamsEmpty(){
		MyHandler server = new MyHandler();
		server.setFinalString("");
		Assert.assertEquals("{}",server.parseQueryString("").toString());
	}
	
	@Test
	public void testhandlehasFinalString() throws Exception{
		HttpExchange httpExchange = null;
		MyHandler handler = mock(MyHandler.class);
		Mockito.doNothing().when(handler).handle(httpExchange);
		
		handler.handle(httpExchange);
	}

}
