package com.darren.center.api.driver.service.impl;

import com.darren.center.api.driver.service.RestTemplateRequestService;
import com.darren.center.api.driver.service.ShortMsgService;
import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.common.dto.sms.SmsSendRequest;
import com.darren.center.service.common.dto.sms.SmsTemplateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年08月12日 10:54:05
 **/
@Service
@Slf4j
public class ShortMsgServiceImpl implements ShortMsgService {

    @Autowired
    private RestTemplateRequestService restTemplateRequestService;

    /**
     * org.springframework.cloud.client.discovery.DiscoveryClient;
     */
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult send(String phoneNumber, String code) {
        log.info("短信发送开始，手机号:{}，验证码:{}", phoneNumber, code);
        String[] phoneNumbers = new String[]{phoneNumber};


        SmsTemplateDto smsTemplateDto = new SmsTemplateDto();
        smsTemplateDto.setId("SMS_110");
        Map<String, Object> map = new HashMap<>(1);
        map.put("code", code);

        List<SmsTemplateDto> data = new ArrayList<>();
        data.add(smsTemplateDto);

        SmsSendRequest smsSendRequest = new SmsSendRequest();
        smsSendRequest.setReceivers(phoneNumbers);
        smsSendRequest.setData(data);
        //正常ribbon调用
        ResponseResult responseResult = restTemplateRequestService.smsSend(smsSendRequest);

        //自定义ribbon调用（注释@LoadBalanced注解）
        /*String serviceName = "service-sms";
        ServiceInstance serviceInstance = myLoadBalance(serviceName);
        String url = "http://" + serviceInstance.getHost()  + ":" + serviceInstance.getPort() + "/send/alisms-template";
        ResponseResult responseResult = restTemplate.postForEntity(url, smsSendRequest, ResponseResult.class).getBody();*/

        log.info("短信发送结束, 发送结果:{}", responseResult);
        return responseResult;
    }

    /**
     * 手写负载均衡算法
     * @param serviceName
     * @return
     */
    private ServiceInstance myLoadBalance(String serviceName){
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        ServiceInstance serviceInstance = instances.get(new Random().nextInt(instances.size()));
        log.info("myLoadBalance，host:{}，port:{}，serviceId:{}, instanceId:{}",
                serviceInstance.getHost(), serviceInstance.getPort(), serviceInstance.getServiceId(), serviceInstance.getInstanceId());
        return serviceInstance;
    }
}
