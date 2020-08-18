package com.darren.center.api.driver.service.impl;

import com.darren.center.api.driver.constant.HttpUrlConstants;
import com.darren.center.api.driver.service.RestTemplateRequestService;
import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.common.dto.sms.SmsSendRequest;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年08月12日 10:38:53
 **/
@Service
@Slf4j
public class RestTemplateRequestServiceImpl implements RestTemplateRequestService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    //@HystrixCommand(fallbackMethod = "sendFail")
    @HystrixCommand(fallbackMethod = "sendFail", commandProperties = {
            @HystrixProperty(name = "fallback.enabled", value = "true") //该属性用来设置服务降级策略是否启用，默认是true
    })
    public ResponseResult smsSend(SmsSendRequest request) {
        String url = HttpUrlConstants.SERVICE_SMS_URL + "/send/alisms-template";
        //请求地址  请求数据  响应类型
        return restTemplate.postForEntity(url, request, ResponseResult.class).getBody();
    }

    /**
     * 此方法的 请求参数和 返回参数 要和原方法一致
     * @param request
     * @return
     */
    public ResponseResult sendFail(SmsSendRequest request) {
        return ResponseResult.fail(-3, "熔断");
    }
}
