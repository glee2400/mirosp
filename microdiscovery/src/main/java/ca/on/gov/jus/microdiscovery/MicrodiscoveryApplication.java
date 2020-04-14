package ca.on.gov.jus.microdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/*
 * Enable Eureka server at server.port=8761
 * Listening all client registration requests
 */
@SpringBootApplication
@EnableEurekaServer
public class MicrodiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrodiscoveryApplication.class, args);
	}

}
