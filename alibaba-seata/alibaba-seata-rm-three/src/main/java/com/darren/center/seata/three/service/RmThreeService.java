package com.darren.center.seata.three.service;

import com.darren.center.seata.three.dao.ThreeDao;
import com.darren.center.seata.three.entity.Three;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年09月02日 17:09:38
 **/
@Service
public class RmThreeService {

    @Autowired
    private ThreeDao threeDao;


    public String rm3(){
        Three rm3 = new Three(1, "rm3");
        threeDao.insertSelective(rm3);
        return "";
    }

}
