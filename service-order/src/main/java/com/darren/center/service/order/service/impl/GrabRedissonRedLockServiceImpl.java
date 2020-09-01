package com.darren.center.service.order.service.impl;

import com.darren.center.service.common.constant.RedisKeyConstant;
import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.order.service.GrabService;
import com.darren.center.service.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>司机抢单</p>
 * Redisson锁 - 红锁
 *
 * service-order/src/main/resources/redis-redlock.md
 *
 * @author : Darren
 * @date : 2020年08月31日 09:55:17
 **/
@Service("grabRedissonRedLockService")
@Slf4j
public class GrabRedissonRedLockServiceImpl implements GrabService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedissonClient redissonClient1;
    @Autowired
    private RedissonClient redissonClient2;
    @Autowired
    private RedissonClient redissonClient3;


    @Override
    public ResponseResult grabOrder(int orderId, int driverId) {
        String lockKey = (RedisKeyConstant.GRAB_LOCK_ORDER_KEY_PRE + orderId).intern();
        RLock rLock1 = redissonClient1.getLock(lockKey);
        RLock rLock2 = redissonClient2.getLock(lockKey);
        RLock rLock3 = redissonClient3.getLock(lockKey);
        RedissonRedLock rLock = new RedissonRedLock(rLock1, rLock2, rLock3);
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
