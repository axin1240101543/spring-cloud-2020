package com.darren.center.api.driver;

import com.darren.center.api.driver.annotation.ExcudeRibbonConfig;
import com.darren.center.api.driver.ribbonconfig.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;



//给所有client设置随机策略
//@RibbonClients(defaultConfiguration = RibbonConfiguration.class)

//给service-sms设置随机策略（需要配置类不被启动类扫描到，否则配置类将对所有client生效）
//@RibbonClient(name = "service-sms", configuration = RibbonConfiguration.class)
//被@ExcudeRibbonConfig注解的类不扫描
//@ComponentScan(
//        basePackages = {"com.darren.center.api.driver"},
//        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {ExcudeRibbonConfig.class}
//                )}
//)
@EnableEurekaClient
//@EnableCircuitBreaker
@EnableHystrix //两个注解@EnableCircuitBreaker，@EnableHystrix点进去看，其实一样。
@SpringBootApplication
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
