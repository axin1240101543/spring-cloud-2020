package com.darren.center.service.order.service;

/**
 * redis锁续约服务
 */
public interface RenewGrabLockService {

    /**
     * 续约
     * @param key
     * @param value
     * @param time
     */
    public void renewLock(String key , String value , int time);

}
