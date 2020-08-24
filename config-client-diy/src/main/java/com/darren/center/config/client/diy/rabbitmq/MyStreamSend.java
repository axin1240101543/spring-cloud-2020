package com.darren.center.config.client.diy.rabbitmq;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>发送</p>
 * Stream是构建消息驱动能力的组件。
 * 可以进行基于消息队列的消息通信，使用Spring Integration连接消息中间件以实现事件驱动。
 * Bus基于Stream。
 *
 * 对于发布/订阅模式模式而言，消息的发送者一般只注重将消息推送到相应的Exchange 对应的Channel中，并不在意订阅者是否成功接收并消费掉某条消息。
 * 消息发布者只负责把消息送到队列中，订阅者只负责把消息从队列中取出然后消费，两者在业务逻辑上理应是不存在任何耦合或关联的，这也是发布/订阅模式的职责和优点所在。
 *
 * @author : Darren
 * @date : 2020年08月24日 16:40:47
 **/
@EnableBinding(Source.class)
@RestController
@RequestMapping("/rabbitmq")
public class MyStreamSend {

    @Resource
    private MessageChannel output;

    @PostMapping("/send")
    public String sendTestData(@RequestBody String content){
        this.output.send(MessageBuilder.withPayload(content).build());
        return "发送成功";
    }

}
