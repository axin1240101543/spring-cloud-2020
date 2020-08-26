package com.darren.center.api.driver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @RefreshScope
 * 刷新配置 http://localhost:8080/actuator/refresh  Content-Type：application/json
 * 缺陷：只能一个一个实例刷新
 *
 * @author : Darren
 * @date : 2020年08月19日 13:49:51
 **/
@RefreshScope
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    /*@Value("${env}")
    private String env;*/

    /**
     * 测试spring cloud config
     * http://localhost:8080/test/getConfig
     * @return
     */
    /*@GetMapping("/getConfig")
    public String getConfig(){
        return env;
    }*/

    /**
     * 测试zuul
     * @param name
     * @param age
     * @return
     */
    @GetMapping("/hello")
    public String hello(@RequestParam String name, @RequestParam  Integer age){
        log.info("name:{}, age:{}", name, age);
        String[] str = new String[]{name, age.toString()};
        return Arrays.toString(str);
    }

    /**
     * 测试zuul - 敏感Header
     * @param req
     * @return
     */
    @RequestMapping("/token")
    public String cookie(HttpServletRequest req) {
        String token = req.getHeader("token");
        System.out.println("token:"+token);
        return "api-driver-token:"+token;
    }

}
