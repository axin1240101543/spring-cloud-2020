package com.darren.center.service.jms.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ServiceJmsConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceJmsConsumerApplication.class, args);
    }

}
