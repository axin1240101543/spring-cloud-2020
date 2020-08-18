package com.darren.center.api.passenger.config;

import com.darren.center.api.passenger.annotation.ExcludeFeignConfig;
import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>禁用feign客户端的hystrix</p>
 * 为@feignclient单独配置Feign.Builder
 *
 * @author : Darren
 * @date : 2020年08月18日 15:23:45
 **/
@Configuration
@ExcludeFeignConfig
public class FeignDisableHystrixConfiguration {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }

}
