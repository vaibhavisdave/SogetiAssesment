package com.sogeti.asses.broker.sogetiAssesBroker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@EnableEurekaClient
@SpringBootApplication
public class SogetiAssesmentBrokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SogetiAssesmentBrokerApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}