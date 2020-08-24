package com.darren.center.config.client.diy.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>事件发布者</p>
 *
 * @author : Darren
 * @date : 2020年08月24日 15:42:30
 **/
@RestController
@RequestMapping("/my-event")
public class PublishController implements ApplicationContextAware, ApplicationEventPublisherAware {


    private ApplicationContext applicationContext;

    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * http://localhost:81/my-event/publish
     * Content-Type:application/json
     * @param content
     * @return
     */
    @PostMapping("/publish")
    public boolean publishEvent(@RequestBody String content) {
        String originService = applicationContext.getId();
        CustomRemoteApplicationEvent event = new CustomRemoteApplicationEvent(content, originService, "destination");
        applicationEventPublisher.publishEvent(event);
        return true;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
