package com.darren.center.api.driver.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>忽略异常</p>
 * 有些情况下，提供者是好的，但在消费者发生业务异常时，我们不希望走熔断的备用方法
 *
 * @author : Darren
 * @date : 2020年08月18日 14:46:06
 **/
public class BusinessException extends HystrixBadRequestException {


    public BusinessException(String message) {
        super(message);
    }

}
