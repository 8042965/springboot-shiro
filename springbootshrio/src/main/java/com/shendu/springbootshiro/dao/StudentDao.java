package com.shendu.springbootshiro.dao;


import com.shendu.springbootshiro.bean.Student;

/**
 * @program: springboot-shiro
 * @description:
 * @author: zhengh
 * @create: 2020-10-25 18:24
 **/

public interface StudentDao {

    //查询学号
    Student findByStudentSno(String sno);

    //根据学号查询授权
    Student findBySno(String Sno);


}
