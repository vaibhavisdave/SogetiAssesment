# SogetiAssesment
This is spring Boot application. it implements microservice.

Components:

1. EurekaServer is registry server. It holds all microservice registry

		Install maven globally, if not already installed.
		
		Steps to start the EurekaServer
		1. on command prompt navigate to the folder location
		2. run command: mvn clean install
		3. run command: java -jar  target/eurekaServer-0.0.1-SNAPSHOT.jar
		4. Enter the url http://localhost:8761/
		------------------------------------------------------------------

2. SogetiAssesmentBroker is microservice for Customer maintained by brokers
   It contains Apis to add, update, delete and select car from H2 database.
   Flow goes like this: Controller <-> service <-> dao

		Install maven globally, if not already installed.
		
		Steps to start the SogetiAssesmentBroker
			1. on command prompt navigate to the folder location
			2. run command: mvn clean install
			3. run command: java -jar  target/SogetiAssesmentBroker-0.0.1-SNAPSHOT.jar
			4. Enter the swagger url http://localhost:8080/swagger-ui/index.html to access the api
		
		Steps to start the SogetiAssesmentLeaseCompany
		 	1. on command prompt navigate to the folder location
			2. run command: mvn test
--------------------------------------------------------------------------------

3. SogetiAssesmentLeaseCompany is microservice for car maintained by Leasing company.
   It contains Apis to add, update, delete and select car from H2 database. 
   Flow goes like this: Controller <-> service <-> dao

		Install maven globally, if not already installed.
		
		Steps to start the SogetiAssesmentLeaseCompany
			1. on command prompt navigate to the folder location
			2. run command: mvn clean install
			3. run command: java -jar  target/SogetiAssesmentLeaseCompany-0.0.1-SNAPSHOT.jar
			4. Enter the swagger url http://localhost:8081/swagger-ui/index.html to access the api
		
		Steps to start the SogetiAssesmentLeaseCompany
		 	1. on command prompt navigate to the folder location
			2. run command: mvn test
