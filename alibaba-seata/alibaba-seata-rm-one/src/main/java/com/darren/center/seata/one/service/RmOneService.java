package com.darren.center.seata.one.service;

import com.darren.center.seata.one.dao.OneDao;
import com.darren.center.seata.one.entity.One;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RmOneService {

    @Autowired
    private OneDao oneDao;

    @Autowired
    private RestTemplate restTemplate;


    public String rm1(){
        rm2();
        rm3();
        One rm1 = new One(1, "rm1");
        oneDao.insertSelective(rm1);
        //异常测试，发生异常时，rm1发生异常时，所有的数据是否回滚
        //int i = 1/0;
        return "";
    }

    public String rm2Update(){
        //rm2 更新
        rm2UpdateRemote();

        One rm1 = new One(1, "rm1");
        oneDao.insertSelective(rm1);
        //异常测试，发生异常时，rm1发生异常时，所有的数据是否回滚
        //int i = 1/0;
        return "";
    }


    /**
     * 调用seata 2 插入一条新数据
     */
    private void rm2() {
        restTemplate.getForEntity("http://seata-two/test/rm2", null);
    }

    /**
     * 调用seata 2 更新数据
     */
    private void rm2UpdateRemote() {
        restTemplate.getForEntity("http://seata-two/test/rm2-update", null);
    }

    /**
     * 调用seata 3
     */
    private void rm3() {
        restTemplate.getForEntity("http://seata-three/test/rm3", null);
    }

}
