package com.darren.center.api.driver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>手写获取元数据</p>
 *
 * @author : Darren
 * @date : 2020年08月11日 16:15:56
 **/
@RestController
@RequestMapping("/service-instance")
@Slf4j
public class ServiceInstanceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 元数据
     * http://localhost:8761/eureka/apps/eureka-client
     * http://localhost:8081/service-instance/query-by-application-name/eureka-client
     * @param applicationName
     * @return
     */
    @GetMapping("/query-by-application-name/{applicationName}")
    public List<ServiceInstance> getInstance(@PathVariable String applicationName){
        return discoveryClient.getInstances(applicationName);
    }

    /**
     * 自定义元数据
     * http://localhost:8081/service-instance/query-by-application-name/my-metadata/eureka-client
     * @param applicationName
     * @return
     */
    @GetMapping("/query-by-application-name/my-metadata/{applicationName}")
    public List<ServiceInstance> getMyMetadata(@PathVariable String applicationName){
        List<ServiceInstance> instances = discoveryClient.getInstances(applicationName);
        for (ServiceInstance instance : instances) {
            Map<String, String> metadata = instance.getMetadata();
            metadata.forEach((k,v) -> log.info("key:{}, key:{}", k, v));
        }
        return instances;
    }

}
