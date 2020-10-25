package com.shendu.springbootshiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: springbootshrio
 * @description:
 * @author: zhengh
 * @create: 2020-10-25 19:15
 **/
@Controller
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "test ok";
    }

}
