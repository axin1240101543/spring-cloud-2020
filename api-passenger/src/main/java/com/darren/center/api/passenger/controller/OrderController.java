package com.darren.center.api.passenger.controller;

import com.alibaba.fastjson.JSONObject;
import com.darren.center.api.passenger.feign.ServiceForecast;
import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.common.dto.order.ForecastRequest;
import com.darren.center.service.common.dto.order.ForecastResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private ServiceForecast serviceForecast;
	
	@PostMapping("/forecast")
	public ResponseResult<ForecastResponse> forecast(@RequestBody ForecastRequest forecastRequest) {
		
		ResponseResult<ForecastResponse> result = serviceForecast.forecast(forecastRequest);
		
		return ResponseResult.success(result.getData());
	}
	
	
	/*
	 * 下面是演示两种调用方式
	 */
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/forecast-test")
	public ResponseResult forecastTest(@RequestBody ForecastRequest forecastRequest) {
		/*
		 * 具体ip(localhost:8060)，不加LoadBalanced
		 * 服务名(service-valuation)，加LoadBalanced
		 */
//		String destination = "localhost:8060";
		String destination = "service-valuation";
		
		String url = "http://"+destination+"/forecast/single";
		
		//请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //请求体
        JSONObject requestParam = new JSONObject();
        requestParam.put("startLatitude", "1");
        requestParam.put("startLongitude", "1");
        requestParam.put("endLatitude", "1");
        requestParam.put("endLongitude", "1");
        //封装成一个请求对象
        HttpEntity entity = new HttpEntity(requestParam, headers);
        
		ResponseResult result = restTemplate.exchange(url, HttpMethod.POST,entity,ResponseResult.class).getBody();
		
		return ResponseResult.success(result.getData());
	}
}
