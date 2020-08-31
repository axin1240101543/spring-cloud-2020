package com.darren.center.service.order.service.impl;

import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.order.service.GrabService;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public ResponseResult grabOrder(int orderId, int driverId) {
        log.info("GrabJVMLockService, 订单ID：{}， 司机ID:{}", orderId, driverId);
        return null;
    }
}
