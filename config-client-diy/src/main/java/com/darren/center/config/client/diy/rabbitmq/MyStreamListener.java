package com.darren.center.config.client.diy.rabbitmq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>监听</p>
 *
 * @author : Darren
 * @date : 2020年08月24日 16:39:09
 **/
@EnableBinding(Sink.class)
public class MyStreamListener {

    @StreamListener(Sink.INPUT)
    public void input(String s){
        System.out.println("监听 消息队列 手动的内容 : " + s);
    }

}
