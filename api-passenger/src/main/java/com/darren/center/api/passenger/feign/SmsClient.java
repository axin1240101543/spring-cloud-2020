package com.darren.center.api.passenger.feign;

import com.darren.center.api.passenger.config.FeignDisableHystrixConfiguration;
import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.common.dto.sms.SmsSendRequest;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
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
//@FeignClient(name = "service-sms", fallback = SmsClient.SmsClientFallback.class)
//@FeignClient(name = "service-sms", fallbackFactory = SmsClient.SmsClientFallbackFactory.class)
@FeignClient(name = "service-sms", configuration = FeignDisableHystrixConfiguration.class, fallbackFactory = SmsClient.SmsClientFallbackFactory.class)
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
            return ResponseResult.fail(-3, "feign fallback熔断");
        }
    }

    @Component
    @Slf4j
    class SmsClientFallbackFactory implements FallbackFactory<SmsClient> {

        @Override
        public SmsClient create(Throwable throwable) {
            return new SmsClient() {
                @Override
                public ResponseResult sendSms(SmsSendRequest smsSendRequest) throws Exception {
                    log.error("异常信息:", throwable);
                    return ResponseResult.fail(-3, "feign fallbackFactory熔断");
                }
            };
        }
    }
}
