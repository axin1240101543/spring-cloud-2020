package com.darren.center.api.driver.controller;

import com.darren.center.api.driver.service.RestTemplateRequestService;
import com.darren.center.service.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>抢单</p>
 *
 * @author : Darren
 * @date : 2020年08月31日 10:11:56
 **/
@RestController
@RequestMapping("/grab")
public class GrabOrderController {

    @Autowired
    private RestTemplateRequestService restTemplateRequestService;

    @GetMapping("/do/{orderId}")
    public ResponseResult grab(@PathVariable("orderId") int orderId, int driverId){
        restTemplateRequestService.grabOrder(orderId, driverId);
        return ResponseResult.success("");
    }

}
