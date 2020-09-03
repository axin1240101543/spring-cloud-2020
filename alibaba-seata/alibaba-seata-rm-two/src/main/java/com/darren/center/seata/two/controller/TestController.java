package com.darren.center.seata.two.controller;

import com.darren.center.seata.two.service.RmTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>spring-cloud-2020</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年09月02日 16:52:41
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RmTwoService rmTwoService;

    @GetMapping("/rm2")
    public String rm2(){
        rmTwoService.rm2();
        return "rm2 success";
    }

    @GetMapping("/rm2-update")
    public String rm2Update(){
        rmTwoService.rm2Update();
        return "rm2 update success";
    }

}
