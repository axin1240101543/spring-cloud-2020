package com.darren.center.service.order.service.impl;

import com.darren.center.service.order.service.RenewGrabLockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>redis锁续约服务</p>
 *
 * @author : Darren
 * @date : 2020年08月31日 15:19:57
 **/
@Service
@Slf4j
public class RenewGrabLockServiceImpl implements RenewGrabLockService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 异步续约（启动类加上@EnableAsync注解）
     *
     * 开个子线程，原来时间N，每个n/3，去续上n
     * @param key
     * @param value
     * @param time
     */
    @Async
    @Override
    public void renewLock(String key, String value, int time) {
        String v = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(v) && v.equals(value)){
            log.info("续期开始， key:{}, value:{}, time:{}", key, value, time);
            int sleepTime = time / 3;
            try {
                Thread.sleep(sleepTime * 1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
            log.info("续期成功， key:{}, value:{}, time:{}", key, value, time);
            renewLock(key, value, time);
        }
    }
}
