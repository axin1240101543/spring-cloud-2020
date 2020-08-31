package com.darren.center;

import com.darren.center.service.common.entity.TblOrder;
import com.darren.center.service.order.ServiceOrderApplication;
import com.darren.center.service.order.dao.TblOrderDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>测试mysql</p>
 *
 * @author : Darren
 * @date : 2020年08月31日 09:39:36
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceOrderApplication.class)
@Slf4j
public class MybatisTest {

    @Autowired
    private TblOrderDao tblOrderDao;

    @Test
    public void testMysql(){
        TblOrder tblOrder = new TblOrder();
        tblOrder.setOrderId(1);
        tblOrder.setStatus(0);
        tblOrderDao.insertSelective(tblOrder);
    }

}
