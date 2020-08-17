package com.darren.center.api.passenger.feign.config;

import com.darren.center.api.passenger.annotation.ExcudeFeignConfig;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年08月17日 14:04:50
 **/
@ExcudeFeignConfig
public class FeignAuthConfiguration {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("root", "root");
    }

}
