package com.darren.center.service.order.lock;

import com.darren.center.service.common.entity.TblOrderLock;
import com.darren.center.service.order.dao.TblOrderLockDao;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>MySQL锁</p>
 *
 * @author : Darren
 * @date : 2020年08月31日 11:16:15
 **/
@Service
@Slf4j
@Data
public class MySQLLock implements Lock {

    @Autowired
    private TblOrderLockDao orderLockDao;

    private ThreadLocal<TblOrderLock> orderLockThreadLocal;


    @Override
    public void lock() {
        // 1、尝试加锁
        if (tryLock()) {
            return;
        }
        // 2.休眠
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 3.递归再次调用
        lock();
    }

    /**
     * 非阻塞式加锁，成功，就成功，失败就失败。直接返回
     *
     * @return
     */
    @Override
    public boolean tryLock() {
        try {
            orderLockDao.insertSelective(orderLockThreadLocal.get());
            log.info("加锁对象:{}", orderLockThreadLocal.get());
            return true;
        } catch (Exception e) {
            //log.info("exception message:{}", e.getMessage());
            return false;
        }
    }

    @Override
    public void unlock() {
        orderLockDao.deleteByPrimaryKey(orderLockThreadLocal.get().getOrderId());
        log.info("解锁对象:{}", orderLockThreadLocal.get());
        orderLockThreadLocal.remove();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
