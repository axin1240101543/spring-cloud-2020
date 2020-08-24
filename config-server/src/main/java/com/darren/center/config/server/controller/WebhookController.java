package com.darren.center.config.server.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>刷新配置</p>
 *
 * @author : Darren
 * @date : 2020年08月21日 17:03:12
 **/
@RestController
@RequestMapping("/githubWebHooks")
@Slf4j
public class WebhookController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 刷新配置
     * @return
     */
    @PostMapping("/refreshConfig")
    public String refresh(){
        log.info("bus refresh begin:~~~~~~~~~~~~~~~~");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://localhost/actuator/bus-refresh", request, String.class);
        log.info("bus refresh result:{}", JSONObject.toJSONString(stringResponseEntity));
        return "bus refresh success!!!";
    }

}
