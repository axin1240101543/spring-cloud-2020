package com.darren.center.service.jms.consumer.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>消息消費者(Queue模式)</p>
 *
 * @author : Darren
 * @date : 2020年09月02日 14:08:59
 **/
@Component
@Slf4j
public class ConsumerQueue {

    /**
     * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
     * @param text
     * @param session
     */
    @JmsListener(destination = "ActiveMQQueue", containerFactory = "jmsListenerContainerFactoryQueue")
    public void receiveQueue1(TextMessage text, Session session){
        try {
            int i = 1/0;
            log.info("receiveQueue1 text message:{}", text.getText());
            //提交完事务后，再确认。因为哪怕下次有事件再来，插库会失败。
            text.acknowledge();
        } catch (JMSException e) {
            log.error("receiveQueue1 exception message:{}", e.getMessage());
            e.printStackTrace();
            try {
                session.recover();
                Thread.sleep(5000);
            } catch (JMSException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


    /**
     * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
     * @param text
     */
    @JmsListener(destination = "ActiveMQQueue", containerFactory = "jmsListenerContainerFactoryQueue")
    public void receiveQueue2(String text){
        log.info("receiveQueue2 text message:{}", text);
    }

}
