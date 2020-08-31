package com.darren.center.service.valuation.controller;

import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/order")
@Slf4j
public class OrderController {

    /**
     * 保存订单
     * @param orderId
     * @param driverId
     * @return
     */
    @GetMapping("/do/{orderId}")
    public String saveOrder(@PathVariable("orderId")String orderId, int driverId){

        return "";
    }

}
