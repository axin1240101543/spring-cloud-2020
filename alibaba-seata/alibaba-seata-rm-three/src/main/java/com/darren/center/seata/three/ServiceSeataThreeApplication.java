package com.darren.center.seata.three;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceSeataThreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSeataThreeApplication.class, args);
    }

}
