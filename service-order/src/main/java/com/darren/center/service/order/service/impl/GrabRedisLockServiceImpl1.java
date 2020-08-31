package com.darren.center.service.order.service.impl;

import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.order.service.GrabService;
import com.darren.center.service.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>司机抢单</p>
 * Redis锁
 * @author : Darren
 * @date : 2020年08月31日 09:55:17
 **/
@Service("grabRedisLockService1")
@Slf4j
public class GrabRedisLockServiceImpl1 implements GrabService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResponseResult grabOrder(int orderId, int driverId) {
        String lock = orderId + "";
        /**
         * 情况一，如果锁没执行到释放，比如业务逻辑执行一半，运维重启服务，或 服务器挂了，没走 finally，怎么办？
         * 加超时时间
         */
        Boolean lockStatus = stringRedisTemplate.opsForValue().setIfAbsent(lock.intern(), driverId + "");
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
            stringRedisTemplate.delete(lock.intern());
        }
        return null;
    }
}
