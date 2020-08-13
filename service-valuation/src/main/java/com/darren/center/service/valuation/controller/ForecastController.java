package com.darren.center.service.valuation.controller;

import com.alibaba.fastjson.JSONObject;
import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.common.dto.order.ForecastRequest;
import com.darren.center.service.common.dto.order.ForecastResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>估价</p>
 *
 * @author : Darren
 * @date : 2020年08月12日 10:45:38
 **/
@RestController
@RequestMapping("/forecast")
@Slf4j
public class ForecastController {

    /**
     * 估价
     * @param forecastRequest
     * @return
     */
    @RequestMapping(value = "/single", method = RequestMethod.POST)
    public ResponseResult forecast(@RequestBody ForecastRequest forecastRequest){
        String s = JSONObject.toJSONString(forecastRequest);
        log.info(s);
        ForecastResponse response = new ForecastResponse();
        Double price = 10.91;
        response.setPrice(price);
        return ResponseResult.success(response);
    }

}
