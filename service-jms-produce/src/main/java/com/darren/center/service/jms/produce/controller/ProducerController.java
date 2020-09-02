package com.darren.center.service.jms.produce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年09月02日 11:15:48
 **/
@RestController
@RequestMapping("/produce")
public class ProducerController {

    /**
     * 注入点对点的模式（Queue模式）
     */
    @Autowired
    private Queue queue;

    /**
     * 注入订阅模式（Topic）的消息
     */
    @Autowired
    private Topic topic;

    /**
     * 注入springboot封装的工具类
     */
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * http://localhost:5001/produce/queue-send?text=aaa
     * 点对点模式（queue）模式发消息
     * @param text
     * @return
     */
    @GetMapping("/queue-send")
    @ResponseBody
    public String queueSend(String text){
        jmsMessagingTemplate.convertAndSend(queue, text);
        return "queue success";
    }

    /**
     * http://localhost:5001/produce/topic-send?text=bbb
     * 订阅模式（topic）发送消息
     * @param text
     * @return
     */
    @GetMapping("/topic-send")
    @ResponseBody
    public String topicSend(String text){
        jmsMessagingTemplate.convertAndSend(topic, text);
        return "topic success";
    }

}
