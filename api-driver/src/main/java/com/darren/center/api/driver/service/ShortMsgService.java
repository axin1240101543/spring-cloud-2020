package com.darren.center.api.driver.service;

import com.darren.center.service.common.dto.ResponseResult;

public interface ShortMsgService {


    /**
     * 发送验证码
     * @param phoneNumber
     * @param code
     * @return
     */
    ResponseResult send(String phoneNumber, String code);

}
