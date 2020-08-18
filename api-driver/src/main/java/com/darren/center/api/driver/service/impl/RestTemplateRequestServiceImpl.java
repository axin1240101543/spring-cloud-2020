package com.darren.center.api.driver.service.impl;

import com.darren.center.api.driver.constant.HttpUrlConstants;
import com.darren.center.api.driver.exception.HystrixIgnoreException;
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
    @HystrixCommand(fallbackMethod = "sendFail",
            ignoreExceptions = {
                HystrixIgnoreException.class
            },
            commandProperties = {
                @HystrixProperty(name = "fallback.enabled", value = "true"), //该属性用来设置服务降级策略是否启用，默认是true
                @HystrixProperty(name = "circuitBreaker.forceOpen", value = "false") //熔断强制配置
    })
    public ResponseResult smsSend(SmsSendRequest request) {

        //测试忽略的异常
        /*try {
            int i = 1/0;
        }catch (Exception e){
            //throw new BusinessException("熔断忽略的异常，继承HystrixBadRequestException");
            throw new HystrixIgnoreException("熔断忽略的异常，忽略属性设置");
        }*/
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

    /**
     * 捕获熔断的异常信息
     * 此方法的 请求参数和 返回参数 要和原方法一致
     * @param request
     * @param throwable
     * @return
     */
    public ResponseResult sendFail(SmsSendRequest request, Throwable throwable) {
        log.error("异常信息:", throwable);
        return ResponseResult.fail(-3, "熔断");
    }
}
