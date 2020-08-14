package com.darren.center.api.driver.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>关闭spring security的csrf</p>
 *  将spring security加入classpath后，默认开启CSRF（cross site request forgery：跨站请求伪造），
 *  要求每次向应用程序发送请求时都会发送一个有效的CSRF令牌，
 * @author : Darren
 * @date : 2020年08月14日 15:03:39
 **/
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf
        http.csrf().disable();
        // 表示所有的访问都必须认证，认证处理后才可以正常进行
        http.httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
        // 所有的rest服务一定要设置为无状态，以提升操作效率和性能
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
