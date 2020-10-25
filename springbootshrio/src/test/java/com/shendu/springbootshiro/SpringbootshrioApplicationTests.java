package com.shendu.springbootshiro;

import com.shendu.springbootshiro.bean.Student;
import com.shendu.springbootshiro.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootshrioApplicationTests {

    @Autowired
    StudentService studentService;

    @Test
    void contextLoads() {
        Student byStudentSno = studentService.findByStudentSno("202001");
        System.out.println(byStudentSno);
    }

}
