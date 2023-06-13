# OpenWeatherMap Test API - DEMO

This repository contains a Java project that demonstrates API testing using HttpClient with Java. It includes positive and negative test cases for the OpenWeatherMap API from the given endpoint. The project uses Maven for dependency management and TestNG as the testing framework. It use data provider to parameterize negative and positive test data for testing.

## Test Cases
This project includes one positive and two negative test cases to demonstrate the functionality and usage of HttpClient for API testing. You can find the test cases in the src/test/java directory.

### Positive Test Cases: 
1. using the valid test data like city for New York city, and longitude and lagitude for the same city, the response code, response status and response body contains city name matched and thus passed the test scenario. The response code is 200, response message is 'OK' and city New York is in response body.

### Negative Test Cases:
2. using the invalid test data for the same city of New York city with invalid longitude and invalid lagitude, the response code and response status matched and thus passed the test scenario. The response code for invalid longitude and latitude for New york is 400, and response message is 'Bad Request'.

## Getting Started

To get started with this project, follow the instructions below.

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven
- Apache testNG
- Docker (optional, for containerization):To containerized the code, run the following commands. 

   ```bash
      -- Build the Docker image:    
          docker build -t my-testapi-demo .
      -- Get IMAGE ID to run the docker
          docker images 
      -- Run the Docker container:
          docker run -it <IMAGE ID>

### Installation

1. Clone the repository:

   ```bash
      git clone https://github.com/ebhakta/testAPI-DEMO.git
   
2. Navigate to the project directory:

   ```bash
      cd testAPI-DEMO
   
3. Build the project using Maven: 

   ```bash
      mvn clean install

## Configuration

Before running the tests, make sure to configure the OpenWeatherMap API key and other parameters in the
config.properties file. You can find this file in the/src/main/java/com/qa/config/config.properties directory. 

 ```bash
api.key=your-api-key
api.base.url=https://api.openweathermap.org/data/2.5/forecast?
lat1=40.7143
lon1=-74.006
lat2=80
lon2=191
lat3=abc
lon3=190

## Running the Tests
 
To execute the API tests, run the following command from the project directory:

   mvn test
      
## You can modify or add more test cases based on your requirements.
