package com.darren.center.service.valuation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceValuationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceValuationApplication.class, args);
    }

}
