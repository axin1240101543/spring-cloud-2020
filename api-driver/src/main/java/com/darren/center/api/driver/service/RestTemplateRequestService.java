package com.darren.center.api.driver.service;

import com.darren.center.service.common.dto.ResponseResult;
import com.darren.center.service.common.dto.sms.SmsSendRequest;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年08月12日 10:35:42
 **/
public interface RestTemplateRequestService {

    /**
     * 发送短信
     * @param request
     * @return
     */
    ResponseResult smsSend(SmsSendRequest request);

    /**
     * 抢单
     * @param orderId
     * @param driverId
     * @return
     */
    String grabOrder(int orderId, int driverId);

}
