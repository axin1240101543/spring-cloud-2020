package com.darren.center.api.passenger.service.impl;

import com.darren.center.api.passenger.feign.SmsClient;
import com.darren.center.api.passenger.service.ShortMsgService;
import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.common.dto.sms.SmsSendRequest;
import com.darren.center.service.common.dto.sms.SmsTemplateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private SmsClient SmsClient;

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
        //ribbon调用
        ResponseResult responseResult = null;
        try {
            responseResult = SmsClient.sendSms(smsSendRequest);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("feign 异常");
        }
        log.info("短信发送结束, 发送结果:{}", responseResult);
        return responseResult;
    }
}
