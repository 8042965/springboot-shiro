package com.shendu.springbootshiro.service;

import com.shendu.springbootshiro.bean.Student;
import org.springframework.stereotype.Service;

/**
 * @program: springboot-shiro
 * @description:
 * @author: zhengh
 * @create: 2020-10-25 18:32
 **/
@Service
public interface StudentService {
    //查询学号
    Student findByStudentSno(String sno);

    //根据学号查询授权
    Student findBySno(String Sno);


}
