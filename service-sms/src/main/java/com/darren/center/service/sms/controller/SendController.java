package com.darren.center.service.sms.controller;

import com.alibaba.fastjson.JSONObject;
import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.common.dto.sms.SmsSendRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>短信发送</p>
 *
 * @author : Darren
 * @date : 2020年08月12日 10:45:38
 **/
@RestController
@RequestMapping("/send")
@Slf4j
public class SendController {

    /**
     * 短信发送
     * @param smsSendRequest
     * @return
     */
    @RequestMapping(value = "/alisms-template", method = RequestMethod.POST)
    public ResponseResult send(@RequestBody SmsSendRequest smsSendRequest){
        String s = JSONObject.toJSONString(smsSendRequest);
        log.info(s);
        try {
            //模拟发送短信
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseResult.success("");
    }

}
