package com.darren.center.api.passenger.feign;

import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.common.dto.order.ForecastRequest;
import com.darren.center.service.common.dto.order.ForecastResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "service-valuation")
public interface ServiceForecast {
	
	@RequestMapping(value = "/forecast/single",method = RequestMethod.POST)
	public ResponseResult<ForecastResponse> forecast(@RequestBody ForecastRequest forecastRequest);
	
}
