# Use an official Maven image as the base image
FROM maven:3.8.4-openjdk-11 AS build

# Set the working directory in the container
WORKDIR /my-maven-repository

# Copy the entire source code to the container
COPY . .

# Specify the command to build and run the Maven project
CMD ["mvn", "test", "-DtestngFile=testng.xml"]
