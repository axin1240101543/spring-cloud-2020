package com.darren.center.api.passenger;

import com.darren.center.api.passenger.annotation.ExcudeFeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

/**
 * 就像是一个开关，只有使用了该注解，OpenFeign相关的组件和配置机制才会生效。还可以对OpenFeign相关组件进行自定义配置
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(
        basePackages = {"com.darren.center.api.passenger"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION,value= ExcudeFeignConfig.class)
        }
)
public class ApiPassengerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPassengerApplication.class, args);
    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
