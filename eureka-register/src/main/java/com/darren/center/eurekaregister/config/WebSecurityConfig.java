package com.darren.center.eurekaregister.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>关闭spring security的csrf</p>
 *  将spring security加入classpath后，默认开启CSRF（cross site request forgery：跨站请求伪造），
 *  要求每次向应用程序发送请求时都会发送一个有效的CSRF令牌，
 *  而Eureka客户端通常不会拥有有效的CSRF令牌，所以需要为/eureka/**禁用此要求。
 *  https://cloud.spring.io/spring-cloud-static/Finchley.SR2/single/spring-cloud.html#_securing_the_eureka_server
 * @author : Darren
 * @date : 2020年08月14日 15:03:39
 **/
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
