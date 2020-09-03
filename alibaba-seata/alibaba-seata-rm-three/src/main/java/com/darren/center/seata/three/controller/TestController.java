package com.darren.center.seata.three.controller;

import com.darren.center.seata.three.service.RmThreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年09月02日 17:09:04
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RmThreeService rmThreeService;

    @RequestMapping("/rm3")
    public String rm3(){
        rmThreeService.rm3();
        return "rm3 success";
    }

}
