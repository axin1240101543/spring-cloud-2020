package com.darren.center.eurekaregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRegisterApplication.class, args);
    }

}
