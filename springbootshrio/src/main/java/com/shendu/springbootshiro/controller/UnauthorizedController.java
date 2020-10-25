package com.shendu.springbootshiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: springbootshrio
 * @description:
 * @author: zhengh
 * @create: 2020-10-25 21:43
 **/
@Controller
public class UnauthorizedController {

    @GetMapping("/unauthorized")
    public String index(){
        return "unauthorized";
    }

}
