package com.darren.center.service.order.controller;

import com.darren.center.service.order.service.GrabService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>订单</p>
 *
 * @author : Darren
 * @date : 2020年08月12日 10:45:38
 **/
@RestController
@RequestMapping("/grab")
@Slf4j
public class GrabOrderController {

    //@Qualifier("grabNoLockService") //无锁
    //@Qualifier("grabJVMLockService") //JVM锁
    //@Qualifier("grabMySQLLockService") //MySQL锁
    @Qualifier("grabRedisLockService1") //Redis锁
    @Autowired
    private GrabService grabService;

    /**
     * 司机抢单
     * @param orderId
     * @param driverId
     * @return
     */
    @GetMapping("/do/{orderId}")
    public String grab(@PathVariable("orderId")int orderId, int driverId){
        grabService.grabOrder(orderId, driverId);
        return "";
    }

}
