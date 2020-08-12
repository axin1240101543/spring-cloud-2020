package com.darren.center.service.common.dto;

import com.darren.center.service.common.enums.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>通用返回值处理类</p>
 *
 * @author : Darren
 * @date : 2020年08月12日 10:04:46
 **/
@Data
@Accessors(chain = true) //可以连续设置值
public class ResponseResult<T> implements Serializable {


    private static final long serialVersionUID = -5245741025496691274L;

    private int code;

    private String message;

    private T data;

    /**
     * 返回成功数据（status：1）
     * @param data 数据内容
     * @param <T>  数据类型
     * @return ResponseResult实例
     */
    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }

    /**
     * 返回错误数据（status：500）
     * @param data 错误内容
     * @param <T> 数据类型
     * @return ResponseResult实例
     */
    public static <T> ResponseResult fail(T data){
        return new ResponseResult().setCode(CommonStatusEnum.EXCEPTION.getCode()).setMessage(CommonStatusEnum.EXCEPTION.getValue()).setData(data);
    }

    public static ResponseResult fail(int code, String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }

    public static ResponseResult fail(int code, String message, String data){
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }
}
