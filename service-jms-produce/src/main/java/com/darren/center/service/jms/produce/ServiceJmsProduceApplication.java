package com.darren.center.service.jms.produce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;


@SpringBootApplication
@EnableJms
public class ServiceJmsProduceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceJmsProduceApplication.class, args);
    }

}
