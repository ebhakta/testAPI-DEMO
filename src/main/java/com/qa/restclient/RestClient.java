package com.qa.restclient;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	
	public CloseableHttpResponse get(String Url) throws ClientProtocolException, IOException{
	    CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(Url); //http get request
		CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL
		
		return closebaleHttpResponse;
			
		}

}
