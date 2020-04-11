# Micro-JTS Spring API Project 
-- with Spring Restful APIs on methods (GET POST PUT DELETE requests) with in-memory DB <br>
-- Set up Person and Addressbook model<br>
-- API documentation with swagger<br>
-- Spring security with OAuth2.0<br>


#### 1. Spring API with basic HTTP GET POST PUT DELETE Restful methods
   a. Start the Micro application from Eclipse by running SpringBoot application <br>
   b. Start the Micro application from the command line, java version must be later than 1.8 <br>
> java -jar spring-boot-api-miro-service-0.0.1-SNAPSHOT.jar <br>

   c. From RESTful API client send HTTP requets with JSON onbject to test Spring API application <br>
   d. Download Postman <br>
>	Test API from url http://localhost:8080/api/v1/person <br>
		http://localhost:8080/ <br>
		http://localhost:8080/ve/api-docs<br> 

#### 2. Swagger-2 documentation
   Launch a browser and visit  __ http://localhost:8080/swagger-ui.html __ <br>
   a. Generate Doc under http://localhost:8080/api/ folder <br>
   b. Only generate base package under ca.on.gov <br>

#### 3. Micro-service communication via RestTemplate synchronized call <br>
   a. RestTemplate remote Rest API synchronized call <br>
   b. API Service discovery - Eureka server <br>
    - eureka server started at http://localhost:8761/
    - jaxb dependemcies(4) must be added manually
    - applicaiton.property need to be updated to start Eureka server properly
    - 
    - Netflix OSS -- leaders in micro-service adn springboot , such as Eureka, Ribbon, Hysterix, Zuul

#### 4. Spring Security OpenID Connect/ OAuth2.0 <br><br>

#### 5. JWT token <br><br>
