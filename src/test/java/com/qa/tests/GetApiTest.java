package com.qa.tests;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.qa.restclient.RestClient;
import com.qa.testbase.TestBase;

public class GetApiTest extends TestBase{
    //defining class variables 
	TestBase testBase;
	String baseUrl;
	String apiKey;
	String apiUrl;
	String lat;
	String lon;
	RestClient restClient;
	JSONObject jsonObject;
	CloseableHttpResponse closeableHttpResponse;
	int statusCode;
	
	@BeforeMethod
	@DataProvider(name = "myDataProvider")
	public void setup(String lat, String lon) {
		testBase = new TestBase();
		baseUrl = prop.getProperty("URL");
		apiKey = prop.getProperty("apiKey");
		
		apiUrl = baseUrl +
				"lat=" + lat +
				 "&lon=" + lon +
				 "&appid=" + apiKey;
		System.out.println(apiUrl);
	}
	@Test(dataProvider = "testData", priority=0) //response code
	public void getResponseCode() throws ClientProtocolException, IOException{
		
		//perform API call
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(apiUrl);
			
		//Status Code:
		statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The response status Code is"+ statusCode);
		
		if (statusCode == RESPONSE_STATUS_CODE_200) {
			//Assert status code
		    Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
		    System.out.println("The actual response code matched with expected response- test passed");
		}else if(statusCode == RESPONSE_STATUS_CODE_400){
			//assert if not 200, verify 400 status code 
		    Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_400, "Status code is not 400");
		    System.out.println("The status code is " + " " + statusCode);
		    System.out.println("The actual response code matched with expected response --> test passed");
		}
	}	
			
	@Test(dataProvider = "testData", priority=1) //response code
	public void getResponseMessage(String lat, String lon) throws ClientProtocolException, IOException{	    
		  //perform API call
			restClient = new RestClient();
			closeableHttpResponse = restClient.get(apiUrl);
			
		  //status message:
			String statusMessage = closeableHttpResponse.getStatusLine().getReasonPhrase();
			System.out.println("Status message--->"+ statusMessage);
		
			//if-else to check the response code and response message and validate as per three test data
			if (statusMessage == RESPONSE_STATUS_MESSAGE_OK) {
				 //Assert response message
			    Assert.assertEquals(statusMessage, RESPONSE_STATUS_MESSAGE_OK, "Response message is not Ok");
			    System.out.println("The actual response message matched with expected response- test passed");
			} else if (statusMessage == RESPONSE_STATUS_MESSAGE_BAD ){
				
			    Assert.assertEquals(statusMessage, RESPONSE_STATUS_MESSAGE_BAD, "Response message is not Bad Request");
			    System.out.println("The actual response message matched with expected response- test passed");  
			    
			 }			    
		}
					
		@Test(dataProvider = "testData", priority =2) //response code
		public void getResponseBody(String lat, String lon) throws ClientProtocolException, IOException{	
			//perform API call
			restClient = new RestClient();
			closeableHttpResponse = restClient.get(apiUrl);
			
			// Json String:
			String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
			JSONObject responseJson = new JSONObject(responseString);
			System.out.println("Response JSON from API---> "+ responseJson);
						
			//convert to string
			String json = responseJson.toString();
			System.out.println("Response JSON from API converted to string ---> "+ json);
			//Fetching values from the 'coord' object within the 'city' object
			
			jsonObject = new JSONObject(json);  
					
			if(statusCode == RESPONSE_STATUS_CODE_200) {	
				
				//Fetching values from the city, name, longitude and latitude object
				JSONObject cityObject = jsonObject.getJSONObject("city");
				String cityName = cityObject.getString("name");
				double latitude = cityObject.getJSONObject("coord").getDouble("lat");
				double longitude = cityObject.getJSONObject("coord").getDouble("lon");
					
				// Printing the fetched values
				System.out.println("City Name: " + cityName);
				System.out.println("Latitude: " + latitude);
				System.out.println("Longitude: " + longitude);
				
				// Assert the values to match city name from response
				Assert.assertEquals(cityName, City, "City name does not match");
				System.out.println("The actual city name matched with actual reponse--> test passed");
				
				//Assert the values to match latitude and longitude
				/*Assert.assertEquals(latitude, lat, "Latitude does not match");
				System.out.println("The latitude matched with reponse--> test passed");
				
				Assert.assertEquals(longitude, lon, "Longitude does not match");
				System.out.println("The longitude matched with reponse--> test passed");*/
			 	
				} else if(statusCode == RESPONSE_STATUS_CODE_400) {
						    		    
					String cod = jsonObject.getString("cod");
					String message = jsonObject.getString("message");

					System.out.println("cod: " + cod);
					System.out.println("message: " + message);
	            
					// Assert the values to match message from response
					if(RESPONSE_STATUS_MESSAGE_lanResponse == message) {
						Assert.assertEquals(message, RESPONSE_STATUS_MESSAGE_lanResponse, "Response message doesn't match");
						System.out.println("The actual response message match with expected reponse--> test passed");
					}
					else if(RESPONSE_STATUS_MESSAGE_lonResponse == message){
						Assert.assertEquals(message, RESPONSE_STATUS_MESSAGE_lonResponse, "Response message doesn't match");
						System.out.println("The actual response message match with expected reponse--> test passed");
					
			}
			}
		}
}

