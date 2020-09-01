package com.darren.center.service.order.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>redis配置</p>
 *
 * @author : Darren
 * @date : 2020年08月28日 16:20:14
 **/
@Component
public class RedisConfig {

    /**
     * 单个redis
     */
    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 单个redisson
     * @return
     */
    /*@Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer()
        .setAddress("127.0.0.1:6379")
        .setDatabase(0);
        //.setPassword("1234");
        return Redisson.create(config);
    }*/

    @Autowired
    private RedisSentinelProperties redisSentinelProperties;

    /**
     *  redisson - 哨兵
     * @return
     */
    @Bean("redisson")
    @Order(1) //先加载RedisSentinelProperties类
    public Redisson getRedisson(){
        Config config = new Config();
        config.useSentinelServers()
              .setMasterName(redisSentinelProperties.getMasterName())
              .addSentinelAddress(redisSentinelProperties.getAddress())
              .setDatabase(0);
              //.setPassword("1234");
        return (Redisson) Redisson.create(config);
    }

    /**
     * 红锁1
     */
    @Bean
    public RedissonClient redissonClient1(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("192.168.244.8:6382")
                .setDatabase(0);
        return Redisson.create(config);
    }

    /**
     * 红锁2
     */
    @Bean
    public RedissonClient redissonClient2(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("192.168.244.8:6383")
                .setDatabase(0);
        return Redisson.create(config);
    }

    /**
     * 红锁3
     */
    @Bean
    public RedissonClient redissonClient3(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("192.168.244.8:6384")
                .setDatabase(0);
        return Redisson.create(config);
    }

}
