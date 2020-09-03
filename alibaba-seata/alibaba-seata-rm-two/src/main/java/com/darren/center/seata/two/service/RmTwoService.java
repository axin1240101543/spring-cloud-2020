package com.darren.center.seata.two.service;

import com.darren.center.seata.two.dao.TwoDao;
import com.darren.center.seata.two.entity.Two;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年09月02日 17:06:00
 **/
@Service
public class RmTwoService {

    @Autowired
    private TwoDao twoDao;

    public String rm2(){
        Two rm2 = new Two(1, "rm2");
        twoDao.insertSelective(rm2);
        //异常测试，发生异常时，rm2发生异常时，所有的数据是否回滚
        //int i = 1/0;
        return "";
    }

    public String rm2Update(){
        List<Two> lists = twoDao.selectAll();
        Two two = lists.get(0);
        two.setName(two.getName() + new Random().nextInt(100));
        twoDao.updateByPrimaryKeySelective(two);
        //异常测试，发生异常时，rm2发生异常时，所有的数据是否回滚
        //int i = 1/0;
        return "";
    }

}
