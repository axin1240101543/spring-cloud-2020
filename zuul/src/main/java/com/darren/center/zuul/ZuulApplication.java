package com.darren.center.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 该注解声明这是一个zuul代理，该代理使用Ribbon来定位注册到eureka server上的微服务，同时，整合了hystrix，实现了容错。
 *
 * http://网关ip:网关端口/服务名/微服务路径
 * http://localhost:9000/api-driver/test/hello?name=darren&age=18  相当于访问 http://localhost:8080/test/hello?name=darren&age=18
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
