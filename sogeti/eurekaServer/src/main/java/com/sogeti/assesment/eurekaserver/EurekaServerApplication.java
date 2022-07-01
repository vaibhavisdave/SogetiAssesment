package com.sogeti.assesment.eurekaserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * EurekaServerApplication.
 *
 * @author vighn
 *
 */
@EnableEurekaServer
@EnableAdminServer
@SpringBootApplication
public class EurekaServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(EurekaServerApplication.class, args);
  }

}
