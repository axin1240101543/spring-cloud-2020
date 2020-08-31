package com.darren.center.service.order.service.impl;

import com.darren.center.service.common.entity.TblOrder;
import com.darren.center.service.order.dao.TblOrderDao;
import com.darren.center.service.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>订单服务</p>
 *
 * @author : Darren
 * @date : 2020年08月31日 09:59:20
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TblOrderDao orderDao;

    @Override
    public boolean grab(int orderId, int driverId) {
        TblOrder order = orderDao.selectByPrimaryKey(orderId);
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        if (order.getStatus().intValue() == 0){
            order.setStatus(1);
            orderDao.updateByPrimaryKeySelective(order);
            return true;
        }
        return false;
    }
}
