package com.shendu.springbootshiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: springbootshrio
 * @description:
 * @author: zhengh
 * @create: 2020-10-25 19:29
 **/
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(String name,Model model){

        model.addAttribute("name",name==null?"深度工作室！":name);

        return "index";
    }

}
