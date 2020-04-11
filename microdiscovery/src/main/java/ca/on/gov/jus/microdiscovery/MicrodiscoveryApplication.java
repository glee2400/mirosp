package ca.on.gov.jus.microdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicrodiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrodiscoveryApplication.class, args);
	}

}
