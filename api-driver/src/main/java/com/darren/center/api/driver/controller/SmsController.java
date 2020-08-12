package com.darren.center.api.driver.controller;

import com.darren.center.api.driver.service.ShortMsgService;
import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.common.enums.CommonStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年08月12日 11:03:52
 **/
@RestController
@RequestMapping("/sms")
@Slf4j
public class SmsController {

    @Autowired
    private ShortMsgService shortMsgService;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     *
     * http://localhost:8080/sms/verify-code/send?phoneNumber=13838380438
     * @Validated ：校验phoneNumber
     * @param phoneNumber
     * @return
     */
    @GetMapping("/verify-code/send")
    public ResponseResult verifyCodeSend(@RequestParam(value = "phoneNumber") String phoneNumber) {
        //String phoneNumber = shortMsgRequest.getPhoneNumber();
        String code = "010101";
        return shortMsgService.send(phoneNumber, code);
    }

    /**
     * ribbon loadbalance 源码
     * http://localhost:8080/sms/choseServiceName
     * @return
     */
    @GetMapping("/choseServiceName")
    public ResponseResult choseServiceName() {
        String serviceId = "service-sms";
        ServiceInstance choose = loadBalancerClient.choose(serviceId);
        log.info("sms节点信息：url:{},port:{}", choose.getHost(), choose.getPort());
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue());
    }

}
