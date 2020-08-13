package com.darren.center.api.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableEurekaClient
@SpringBootApplication
//被@ExcudeRibbonConfig注解的类不扫描
//@ComponentScan(
//        basePackages = {"com.darren.center.api.driver"},
//        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {ExcudeRibbonConfig.class}
//        )}
//)
//给所有client设置随机策略
//@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class ApiDriverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiDriverApplication.class, args);
    }


    /**
     * 需要加上这个注解，否则不能负载均衡，且报错 -> java.net.UnknownHostException: SERVICE-SMS
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
