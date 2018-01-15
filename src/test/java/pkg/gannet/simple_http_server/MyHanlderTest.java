package pkg.gannet.simple_http_server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
	public void checkProcessParametersSetFinalStringifInvalid() throws IOException {
		MyHandler server = new MyHandler();
		server.setFinalString("");
		Map<String,String> queryParams = new HashMap();
		String inputString = "Demo";
		server.processParameters(queryParams,inputString);
		Assert.assertEquals("Invalid Request\nNo Query Parameters Provided",server.getFinalString());
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
	public void checkParseQueryStringReturnCorrectMapIfNull(){
		MyHandler server = new MyHandler();
		server.setFinalString(null);
		Assert.assertEquals("{}",server.parseQueryString("").toString());
	}
	
	@Test
	public void checkParseQueryStringReturnCorrectMapIfInvalidQueryString(){
		MyHandler server = new MyHandler();
		server.setFinalString("&");
		Assert.assertEquals("{}",server.parseQueryString("").toString());
	}
	
	
	
	@Test
	public void testhandlehasFinalString() throws Exception{
		HttpExchange httpExchange = mockHttpExchange("http://localhost:8004/hello","GET","");
		
		MyHandler handler = new MyHandler();
		
		handler.handle(httpExchange);
		Assert.assertEquals("Hello\nNo Query Parameters Provided",handler.getFinalString());
	}
	
	
	private HttpExchange mockHttpExchange(String uri, String requestMethod, String body) throws URISyntaxException, IOException {

        HttpExchange httpExchange = mock(HttpExchange.class);

        URI requestURI = new URI(uri);

        Mockito.when(httpExchange.getRequestURI()).thenReturn(requestURI);
        Mockito.when(httpExchange.getRequestMethod()).thenReturn(requestMethod);

        if (body != null) {
            InputStream inputStream = new ByteArrayInputStream( body.getBytes() );
            Mockito.when(httpExchange.getRequestBody()).thenReturn(inputStream);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Mockito.when(httpExchange.getResponseBody()).thenReturn(out);

        return httpExchange;
    }

}
