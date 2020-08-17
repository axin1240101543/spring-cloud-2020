package com.darren.center.api.passenger.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年08月17日 14:36:37
 **/
public class MyBasicAuthRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //https://blog.csdn.net/Ee01114295/article/details/82226994
        //Authorization header的典型数据为"Authorization: Basic cm9vdDpyb290"，其中Basic表示基础认证， cm9vdDpyb290是base64编码的"root:root"字符串。
        requestTemplate.header("Authorization", "Basic cm9vdDpyb290");
    }
}
