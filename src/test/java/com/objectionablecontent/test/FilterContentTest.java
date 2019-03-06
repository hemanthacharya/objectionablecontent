package com.objectionablecontent.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FilterContentTest {

	@Test
	public void testRegex() {

		String inp = "there s a SeXed right here";
		String inp2 = "there is no vulgur here";
		String inpCaseInsensitive = "NiGger";
		String regex = "(.*nigger.*)";
		String regex2 = "(.*sex.*|.*fuck.*|.*rape.*|.*drug.*|.*molest.*)";
		Pattern pat = Pattern.compile(regex2, Pattern.CASE_INSENSITIVE);
		Pattern patCaseInsensitive = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

		assertTrue(pat.matcher(inp).matches());
		assertFalse(pat.matcher(inp2).matches());
		assertTrue(patCaseInsensitive.matcher(inpCaseInsensitive).matches());
	}
	
	@Ignore
	public void testFilterApi() { 
		
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("http://localhost:8080/objectionablecontent/api/content/check");
	 
	    String json = "{\"text\": \"Nigger\"}";
	    StringEntity entity = null;
		try {
			entity = new StringEntity(json);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    httpPost.setEntity(entity);
	    httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
	 
	    CloseableHttpResponse response = null;
		try {
			response = client.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    assertEquals(response.getStatusLine().getStatusCode(), 200);
	    
	    ObjectMapper ob = new ObjectMapper();
	    try {
			JsonNode root = ob.readTree(response.getEntity().getContent());
			
			assertNotNull(root.path("contentType"));
			assertEquals(root.path("contentType").isArray(), true);
			
			List<String> contentTypes = new ArrayList<String>();
			
			for (JsonNode type : root.path("contentType")) {
				contentTypes.add(type.asText());
			}
			
			assertFalse(contentTypes.isEmpty());
			assertTrue(root.path("offensive").asBoolean());
			
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedOperationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
