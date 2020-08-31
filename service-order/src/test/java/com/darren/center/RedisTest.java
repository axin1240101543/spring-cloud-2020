package com.darren.center;

import com.darren.center.service.order.ServiceOrderApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>测试redis</p>
 *
 * @author : Darren
 * @date : 2020年08月28日 16:40:07
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceOrderApplication.class)
@Slf4j
public class RedisTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedis(){
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent("darren", "123456");
        log.info("result:{}", result);
    }

}
