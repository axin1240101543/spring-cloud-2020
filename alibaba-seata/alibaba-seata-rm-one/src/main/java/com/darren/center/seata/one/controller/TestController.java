package com.darren.center.seata.one.controller;

import com.darren.center.seata.one.service.RmOneService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年09月02日 15:56:03
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RmOneService rmOneService;

    /**
     * 注册中心 http://localhost:8761/
     *
     * http://192.168.229.1:8001/test/rm1
     * @return
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    @GetMapping("/rm1")
    public String rm1(){
        rmOneService.rm1();
        return "rm1 success";
    }

    /**
     * http://192.168.229.1:8001/test/rm1-update
     * @return
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    @GetMapping("/rm1-update")
    public String rm1Update(){
        rmOneService.rm2Update();
        return "rm1 update success";
    }

}
