package com.darren.center.config.client.diy.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>事件</p>
 *
 * @author : Darren
 * @date : 2020年08月24日 15:39:44
 **/
public class CustomRemoteApplicationEvent extends RemoteApplicationEvent {

    /**
     *
     * @param source
     * @param originService 事件源
     * @param destinationService 事件目的服务
     */
    public CustomRemoteApplicationEvent(Object source, String originService, String destinationService) {
        super(source, originService, destinationService);
    }
}
