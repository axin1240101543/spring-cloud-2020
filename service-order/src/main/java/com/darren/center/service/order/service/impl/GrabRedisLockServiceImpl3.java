package com.darren.center.service.order.service.impl;

import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.order.service.GrabService;
import com.darren.center.service.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>司机抢单</p>
 * Redis锁
 * @author : Darren
 * @date : 2020年08月31日 09:55:17
 **/
@Service("grabRedisLockService3")
@Slf4j
public class GrabRedisLockServiceImpl3 implements GrabService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResponseResult grabOrder(int orderId, int driverId) {
        String lock = orderId + "";
        /**
         * 情况三：占有锁的线程还没处理完锁就释放了，然后其他的线程抢到锁之后，原来占用锁的线程把锁给释放了
         * 开个子线程，原来时间N，每个n/3，去续上n
         * 每个线程只能释放自己的锁
         */
        Boolean lockStatus = stringRedisTemplate.opsForValue().setIfAbsent(lock.intern(), driverId + "", 30L, TimeUnit.SECONDS);
        if (!lockStatus){
            return null;
        }
        try {
            log.info("司机:{}开始抢单:{}", driverId, orderId);
            boolean b = orderService.grab(orderId, driverId);
            if (b){
                log.info("司机:{}结束抢单:{}抢单成功", driverId, orderId);
            }else {
                log.info("司机:{}结束抢单:{}抢单失败", driverId, orderId);
            }
        }finally {
            /**
             * 这种释放锁有，可能释放了别人的锁。
             */
            //stringRedisTemplate.delete(lock.intern());

            /**
             * 避免释放别人的锁
             */
            if ((driverId + "").equals(stringRedisTemplate.opsForValue().get(lock.intern()))){
                stringRedisTemplate.delete(lock.intern());
            }
        }
        return null;
    }
}
