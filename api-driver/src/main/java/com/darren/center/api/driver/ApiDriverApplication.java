package com.darren.center.api.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class ApiDriverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiDriverApplication.class, args);
    }


    /**
     * 需要加上这个注解，否则不能负载均衡，且报错 -> 未知主机名
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
