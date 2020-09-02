package com.darren.center.service.jms.consumer.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年09月02日 13:53:24
 **/
@Configuration
public class ActiveMQConfig {

    @Value("${spring.activemq.broker-url}")
    private  String brokerUrl;

    @Bean
    public RedeliveryPolicy redeliveryPolicy(){
        RedeliveryPolicy  redeliveryPolicy=   new RedeliveryPolicy();
        return redeliveryPolicy;
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(RedeliveryPolicy redeliveryPolicy){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("admin", "admin", brokerUrl);
        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        return activeMQConnectionFactory;
    }

    /**
     * Queue模式连接注入
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactoryQueue(ActiveMQConnectionFactory activeMQConnectionFactory){
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConnectionFactory(activeMQConnectionFactory);
        /**
         * 客户端手动确认，这就意味着AcitveMQ将不会自动ACK任何消息。
         * 如果一个conmuser在消费结束前没有调用message.acknowledge()确认一个消息，
         * 之后调用其他conmuser时会再次消费它，因为对于broker而言，那些尚未真正ACK的消息被视为未消费，
         * 直到它被确认。
         */
        defaultJmsListenerContainerFactory.setSessionAcknowledgeMode(2);
        return defaultJmsListenerContainerFactory;
    }

    /**
     * Topic模式连接注入
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactoryTopic(ActiveMQConnectionFactory activeMQConnectionFactory){
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConnectionFactory(activeMQConnectionFactory);
        /**
         * 设置为发布订阅方式, 默认情况下使用的生产消费者方式
         */
        defaultJmsListenerContainerFactory.setPubSubDomain(true);
        return defaultJmsListenerContainerFactory;
    }

}
