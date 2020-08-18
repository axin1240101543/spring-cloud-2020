package com.darren.center.api.passenger.feign;

import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.common.dto.sms.SmsSendRequest;
import com.darren.center.service.common.enums.CommonStatusEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年08月12日 14:40:32
 **/
@FeignClient(name = "service-sms", fallback = SmsClient.SmsClientFallback.class)
public interface SmsClient {

    /**
     * 按照短信模板发送验证码
     * @param smsSendRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/send/alisms-template", method = RequestMethod.POST)
    ResponseResult sendSms(@RequestBody SmsSendRequest smsSendRequest) throws Exception;

    @Component
    class SmsClientFallback implements SmsClient{
        @Override
        public ResponseResult sendSms(SmsSendRequest smsSendRequest) throws Exception {
            return ResponseResult.fail(-3, "feign熔断");
        }
    }
}
