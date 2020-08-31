package com.darren.center.service.order.service.impl;

import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.order.service.GrabService;
import com.darren.center.service.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>司机抢单</p>
 * Redis锁
 * @author : Darren
 * @date : 2020年08月31日 09:55:17
 **/
@Service("grabRedissonLockService")
@Slf4j
public class GrabRedissonLockServiceImpl implements GrabService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public ResponseResult grabOrder(int orderId, int driverId) {
        String lock = orderId + "";
        RLock rLock = redissonClient.getLock(lock.intern());

        try {
            //此代码默认 设置key 超时时间30秒，过10秒，再延时
            rLock.lock();
            log.info("司机:{}开始抢单:{}", driverId, orderId);
            boolean b = orderService.grab(orderId, driverId);
            if (b){
                log.info("司机:{}结束抢单:{}抢单成功", driverId, orderId);
            }else {
                log.info("司机:{}结束抢单:{}抢单失败", driverId, orderId);
            }
        }finally {
            rLock.unlock();
        }
        return null;
    }
}
