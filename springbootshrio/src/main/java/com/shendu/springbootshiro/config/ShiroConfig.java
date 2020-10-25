package com.shendu.springbootshiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: springboot-shiro
 * @description:
 * @author: zhengh
 * @create: 2020-10-25 17:02
 **/
@Configuration
public class ShiroConfig {

    private static final transient Logger log = LoggerFactory.getLogger(ShiroConfig.class);


    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager securityManager){

        log.info("进入到了：getShiroFilterFactoryBean中");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //1、设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //2、添加Shito内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *  常用的几种：
         *      1、anon：无需认证（登录）就可以访问
         *      2、authc：必须认证才可以访问
         *      3、user：如果使用remeberMe的功能可以直接访问
         *      4、perms：该资源必须得到资源权限才可以访问
         *      5、role：：该资源必须得到角色权限才可以访问
         */
        //定义存放认证规则的map
        Map<String, String> filterMap = new HashMap<>();
        //对单个url进行拦截  拦截add和update页面的访问，必须认证才可访问
//        filterMap.put("/add","authc");
//        filterMap.put("/update","authc");

        //拦截所有的,进行认证
        filterMap.put("/*","authc");

        //放开主页面
        filterMap.put("/index","anon");
        //放开登录页面
        filterMap.put("/user/login","anon");
        //放开登录提交数据的接口
        filterMap.put("/login","anon");

        //针对perms：拦截/add路径
        filterMap.put("/add","perms[user:add]");
        filterMap.put("/update","perms[user:update]");

        //设置未授权的页面url
        shiroFilterFactoryBean.setUnauthorizedUrl("unauthorized");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        shiroFilterFactoryBean.setLoginUrl("/user/login");

        return shiroFilterFactoryBean;
    }





    /**
     * 创建 DefaultWebSecurityManager
     * @param userRealm
     * @return
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        //关联realm
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);

        return securityManager;
    }


    /**
     * 创建Realm
     * @return
     */
    @Bean(name = "userRealm")
    public UserRealm getRelm(){
        return new UserRealm();
    }

}
