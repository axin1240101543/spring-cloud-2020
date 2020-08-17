package com.darren.center.service.valuation.controller;

import com.alibaba.fastjson.JSONObject;
import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.common.dto.order.ForecastRequest;
import com.darren.center.service.common.dto.order.ForecastResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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

    @GetMapping(value = "/single1")
    public ResponseResult forecast1(@RequestParam("id") long id){
        log.info("id:{}", id);
        ForecastResponse response = new ForecastResponse();
        Double price = (double)id;
        response.setPrice(price);
        return ResponseResult.success(response);
    }

    @GetMapping(value = "/single2")
    public ResponseResult forecast2(@RequestParam Map<String, Object> map){
        log.info("map:{}", JSONObject.toJSONString(map));
        ForecastResponse response = new ForecastResponse();
        Double price = 99.99;
        response.setPrice(price);
        return ResponseResult.success(response);
    }

}
