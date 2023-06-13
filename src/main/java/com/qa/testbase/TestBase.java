package com.qa.testbase;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

import org.testng.annotations.DataProvider;

public class TestBase {

	public int RESPONSE_STATUS_CODE_200 = 200;
	public String RESPONSE_STATUS_MESSAGE_OK = "OK";
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public String RESPONSE_STATUS_MESSAGE_BAD = "Bad Request";
	public String RESPONSE_STATUS_MESSAGE_lonResponse = "wrong longitude";
	public String RESPONSE_STATUS_MESSAGE_lanResponse = "wrong latitude";
	public String City = "New York";

	public Properties prop;
	
	
	@DataProvider(name = "testData")
	public Object[][] provideTestData() {
	    // Read the config file and retrieve the necessary test data
	    prop = new Properties();
	    try {
	    	FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+
			"/src/main/java/com/qa/config/config.properties");
			prop.load(ip);
	        ip.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // test data from the config file
	    Object[][] testData = {
	        { prop.getProperty("lat1"), prop.getProperty("lon1") },
	        { prop.getProperty("lat2"), prop.getProperty("lon2")  },
	        { prop.getProperty("lat3"), prop.getProperty("lon3")  },
	        // Add more test data as needed
	    };

	    return testData;
	}
}
