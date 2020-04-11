package ca.on.gov.jus.micro;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * Micro JTS Spring API Project
 * Swagger2 is added as a annotation, however,
 * for a real project it will be in a configuration class 
 *
 */

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class MicrojtsApplication {
	
	/*Inject a Bean and only initiate it once. as long as it is in the class path*/
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(MicrojtsApplication.class, args);
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		//Return a prepared Docket instance
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/**"))	//only generate Doc under http://localhost:8080/api/ folder
				.apis(RequestHandlerSelectors.basePackage("ca.on.gov"))  //only generate base package under ca.on.gov, no modules,etc.
				.build()
				.apiInfo(apiDetails()) //after the build(), before return the Docket bean; Method to include many info.
				;
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Micro JTS - API",
				"API for Micro JTS Smaple Application\n"
				+ "Micro JTS Spring API project with Restful APIs on methods (GET POST PUT DELETE requests) with in-memory DB",
				"1.0",
				"JTS API use only",
				new springfox.documentation.service.Contact("Glee Jts", "https://localhost:8080/glee/", "G@lee.com"),
				"Micro JTS API License",
				"https://localhost:8080/glee/",
				Collections.emptyList()
				);
	}

}
