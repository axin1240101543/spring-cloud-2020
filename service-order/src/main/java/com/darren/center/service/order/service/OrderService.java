package com.darren.center.service.order.service;

/**
 * 订单服务
 */
public interface OrderService {

    /**
     * 更新订单
     * @param orderId
     * @param driverId
     * @return
     */
    public boolean grab(int orderId, int driverId);

}
