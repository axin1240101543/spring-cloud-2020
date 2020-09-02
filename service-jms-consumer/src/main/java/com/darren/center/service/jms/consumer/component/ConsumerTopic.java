package com.darren.center.service.jms.consumer.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>消息消費者（Topic模式）</p>
 *
 * @author : Darren
 * @date : 2020年09月02日 14:08:59
 **/
@Component
@Slf4j
public class ConsumerTopic {

    /**
     * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
     * @param text
     */
    @JmsListener(destination = "ActiveMQTopic", containerFactory = "jmsListenerContainerFactoryTopic")
    public void receiveTopic1(String text){
        log.info("receiveTopic1 text message:{}", text);
    }

    /**
     * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
     * @param text
     */
    @JmsListener(destination = "ActiveMQTopic", containerFactory = "jmsListenerContainerFactoryTopic")
    public void receiveTopic2(String text){
        log.info("receiveTopic2 text message:{}", text);
    }

}
