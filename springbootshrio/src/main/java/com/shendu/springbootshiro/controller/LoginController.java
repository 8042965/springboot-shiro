package com.shendu.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: springboot-shiro
 * @description:
 * @author: zhengh
 * @create: 2020-10-25 17:33
 **/
@Controller
public class LoginController {
    private static final transient Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/user/login")
    public String index(){
        return "user/login";
    }

    /**
     * 登录接口
     * @return
     */
    @RequestMapping("/login")
    public String login(String name, String password, Model model) {

        log.info("正在登录啦啦啦");

        //使用Shiro编写认证的逻辑
        //1、获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        //3、执行登录方法
        try {
            subject.login(token);
            model.addAttribute("msg", "login success");

            log.info("登陆成功啦");
            //登陆成功
            return "redirect:/index";

            //用户名不存在
        } catch (UnknownAccountException uae) {
            log.info("There is no user with username of " + token.getPrincipal());
            model.addAttribute("msg", "There is no user with username of");

            return "/user/login";
            //密码错误
        } catch (IncorrectCredentialsException ice) {
            log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            model.addAttribute("msg", "Password for account " + token.getPrincipal() + " was incorrect!");

            return "/user/login";
        } catch (LockedAccountException lae) {
            log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
            model.addAttribute("msg", "The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");

            return "/user/login";
        } catch (AuthenticationException ae) {
            //unexpected condition?  error?
            return "/user/login";
        }
    }


}
