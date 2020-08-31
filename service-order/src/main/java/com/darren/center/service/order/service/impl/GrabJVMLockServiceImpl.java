package com.darren.center.service.order.service.impl;

import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.order.service.GrabService;
import com.darren.center.service.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>司机抢单</p>
 * JVM锁
 * @author : Darren
 * @date : 2020年08月31日 09:55:17
 **/
@Service("grabJVMLockService")
@Slf4j
public class GrabJVMLockServiceImpl implements GrabService {

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseResult grabOrder(int orderId, int driverId) {
        String lock = orderId + "";

        synchronized (lock.intern()){
            try {
                log.info("司机:{}开始抢单:{}", driverId, orderId);
                boolean b = orderService.grab(orderId, driverId);
                if (b){
                    log.info("司机:{}结束抢单:{}抢单成功", driverId, orderId);
                }else {
                    log.info("司机:{}结束抢单:{}抢单失败", driverId, orderId);
                }
            }finally {

            }
        }
        return null;
    }
}
