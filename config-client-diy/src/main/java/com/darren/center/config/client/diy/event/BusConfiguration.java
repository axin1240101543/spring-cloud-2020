package com.darren.center.config.client.diy.event;

import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>事件订阅者</p>
 *
 * @author : Darren
 * @date : 2020年08月24日 15:38:26
 **/
@Configuration
@RemoteApplicationEventScan
public class BusConfiguration {

    @EventListener
    public void onUserRemoteApplicationEvent(CustomRemoteApplicationEvent event){
        System.out.println("原始服务："+event.getOriginService()+",内容："+event.getSource());
    }

}
