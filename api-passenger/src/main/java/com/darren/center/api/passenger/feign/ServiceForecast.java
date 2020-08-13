package com.darren.center.api.passenger.feign;

import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.common.dto.order.ForecastRequest;
import com.darren.center.service.common.dto.order.ForecastResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//此处由于结合了eureka，所以name是 虚拟主机名，默认服务名，请求时 会将它解析成注册表中的服务。
//不结合eureka，就是自定义一个client名字。就用url属性指定 服务器列表。url=“http://ip:port/”
//此时的name作用就是创建负载均衡器。
//也可以添加@RequestMapping
@FeignClient(name = "service-valuation")
public interface ServiceForecast {
	
	@RequestMapping(value = "/forecast/single",method = RequestMethod.POST)
	public ResponseResult<ForecastResponse> forecast(@RequestBody ForecastRequest forecastRequest);
	
}
