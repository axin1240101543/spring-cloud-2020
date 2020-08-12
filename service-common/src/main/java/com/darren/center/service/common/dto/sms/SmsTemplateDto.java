package com.darren.center.service.common.dto.sms;

import lombok.Data;

import java.util.Map;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>短信模板实体</p>
 *
 * @author : Darren
 * @date : 2020年08月12日 10:33:15
 **/
@Data
public class SmsTemplateDto {

    /**
     * 短信模板id
     */
    private String id;

    /**
     * 短信模板内容
     */
    private Map<String, Object> map;

    @Override
    public String toString() {
        return "SmsTemplateDto{" +
                "id='" + id + '\'' +
                ", map=" + map +
                '}';
    }
}
