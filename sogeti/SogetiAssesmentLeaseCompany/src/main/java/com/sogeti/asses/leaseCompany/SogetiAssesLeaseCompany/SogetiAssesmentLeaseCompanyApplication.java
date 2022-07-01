package com.sogeti.asses.leaseCompany.SogetiAssesLeaseCompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * SogetiAssesmentLeaseCompanyApplication.
 *
 * @author vighn
 *
 */
@EnableEurekaClient
@SpringBootApplication
public class SogetiAssesmentLeaseCompanyApplication {

  public static void main(String[] args) {
    SpringApplication.run(SogetiAssesmentLeaseCompanyApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
