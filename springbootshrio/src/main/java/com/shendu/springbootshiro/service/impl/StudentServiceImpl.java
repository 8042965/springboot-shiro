package com.shendu.springbootshiro.service.impl;

import com.shendu.springbootshiro.bean.Student;
import com.shendu.springbootshiro.dao.StudentDao;
import com.shendu.springbootshiro.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: springboot-shiro
 * @description:
 * @author: zhengh
 * @create: 2020-10-25 18:33
 **/

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;


    @Override
    public Student findByStudentSno(String sno) {
        return studentDao.findByStudentSno(sno);
    }

    @Override
    public Student findBySno(String Sno) {
        return studentDao.findBySno(Sno);
    }


}
