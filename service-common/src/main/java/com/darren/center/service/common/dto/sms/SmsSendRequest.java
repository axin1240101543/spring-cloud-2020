package com.darren.center.service.common.dto.sms;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>短信发送实体</p>
 *
 * {
 *   "receivers": [
 *     "13412341234","手机号"
 *   ],
 *   "data": [
 *     {
 *       "id": "SMS_144145499",
 *       "templateMap": {
 *         "code": "9876"
 *       }
 *     }
 *   ]
 * }
 *
 * @author : Darren
 * @date : 2020年08月12日 10:32:12
 **/
@Data
public class SmsSendRequest {

    /**
     * 接受者集合
     */
    private String[] receivers;

    /**
     * 短信模板集合
     */
    private List<SmsTemplateDto> data;

    @Override
    public String toString() {
        return "SmsSendRequest{" +
                "receivers=" + Arrays.toString(receivers) +
                ", data=" + data +
                '}';
    }
}
