package com.shendu.springbootshiro.config;

import com.shendu.springbootshiro.bean.Student;
import com.shendu.springbootshiro.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: springboot-shiro
 * @description:
 * @author: zhengh
 * @create: 2020-10-25 17:03
 **/

public class UserRealm extends AuthorizingRealm {
    private static final transient Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    StudentService studentService;

    /**
     * 执行授权的逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("我进入到了授权的逻辑当中");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源的授权字符串
//        info.addStringPermission("user:add");
        //到数据库中查询当前登录用户的权限
        //1、拿到传递过来的用户数据
        Subject subject = SecurityUtils.getSubject();
        Student student = (Student)subject.getPrincipal();
        //2、根据用户信息，查询是否授权
        Student ps = studentService.findBySno(student.getSno());
        log.info("查询授权的用户："+ps);
        info.addStringPermission(ps.getPerms());
        return info;
    }





    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("我进入到了认证的逻辑当中");
        /*
             ---------> 第一种：写死账号密码测试用   <----------
         */
       /* //假设数据库的用户名和密码
        String name = "admin";
        String password = "admin";

        //编写shiro判断逻辑，判断用户名和密码

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        //账号密码都正确
        if(token.getUsername().equals(name) && token.getPassword().equals(password)){
            //用户名不存在
            log.info("用户名不存在----");
            return null; //shiro底层会抛出异常，进行处理
        }

        //此处密码需为数据库中存放的正确的密码
        return new SimpleAuthenticationInfo("",password,"");*/
         /*
             ---------> 第二种：去数据库中查询   <----------
         */
        //假设数据库的用户名和密码

        //编写shiro判断逻辑，判断用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String sno = token.getUsername() == null ? "" : token.getUsername();
        String password = token.getPassword() == null ? "" : token.getPassword().toString() ;
        Student byStudentSno = studentService.findByStudentSno(sno);
        log.info("从数据库中查到的:"+byStudentSno.toString());
        //账号正确
        if(byStudentSno==null){
            //用户名不存在
            log.info("用户名不存在----");
            return null; //shiro底层会抛出异常，进行处理
        }
        //此处密码需为数据库中存放的正确的密码
        return new SimpleAuthenticationInfo(byStudentSno,byStudentSno.getPassword(),"");
    }

}
