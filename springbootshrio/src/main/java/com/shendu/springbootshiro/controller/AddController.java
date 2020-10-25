package com.shendu.springbootshiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: springboot-shiro
 * @description:
 * @author: zhengh
 * @create: 2020-10-25 17:19
 **/

@Controller
public class AddController {

    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }

}
