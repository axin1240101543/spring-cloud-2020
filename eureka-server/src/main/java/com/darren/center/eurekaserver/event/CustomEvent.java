package com.darren.center.eurekaserver.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>自定义事件</p>
 *
 *  EurekaInstanceCanceledEvent 服务下线事件
 *  EurekaInstanceRegisteredEvent 服务注册事件
 *  EurekaInstanceRenewedEvent 服务续约事件
 *  EurekaRegistryAvailableEvent 注册中心可用事件
 *  EurekaServerStartedEvent  注册中心启动
 *
 * @author : Darren
 * @date : 2020年08月11日 22:16:48
 **/
@Component
@Slf4j
public class CustomEvent {

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event){
        log.info("服务下线事件:{}", event.getServerId());
    }

}

