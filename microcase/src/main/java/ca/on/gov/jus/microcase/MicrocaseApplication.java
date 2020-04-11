package ca.on.gov.jus.microcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicrocaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrocaseApplication.class, args);
	}

}
