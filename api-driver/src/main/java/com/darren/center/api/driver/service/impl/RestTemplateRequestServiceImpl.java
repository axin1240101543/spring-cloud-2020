package com.darren.center.api.driver.service.impl;

import com.darren.center.api.driver.constant.HttpUrlConstants;
import com.darren.center.api.driver.service.RestTemplateRequestService;
import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.common.dto.sms.SmsSendRequest;
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
    public ResponseResult smsSend(SmsSendRequest request) {
        String url = HttpUrlConstants.SERVICE_SMS_URL + "/send/alisms-template";
        //请求地址  请求数据  响应类型
        return restTemplate.postForEntity(url, request, ResponseResult.class).getBody();
    }
}
