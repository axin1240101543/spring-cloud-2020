package com.darren.center.service.common.dto.sms;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年08月12日 11:07:32
 **/
@Data
public class ShortMsgRequest {

    @NotNull(message = "手机号不能为空")
    @Pattern(message = "手机号校验不正确",regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$")
    private String phoneNumber;

}
