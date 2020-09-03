package com.darren.center.seata.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceSeataTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSeataTwoApplication.class, args);
    }

}
