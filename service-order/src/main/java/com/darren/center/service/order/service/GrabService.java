package com.darren.center.service.order.service;

import com.darren.center.service.common.dto.ResponseResult;

/**
 * 抢单服务
 */
public interface GrabService {

    /**
     * 司机抢单
     *
     * @param orderId 订单id
     * @param driverId 司机id
     * @return
     */
    public ResponseResult grabOrder(int orderId, int driverId);

}
