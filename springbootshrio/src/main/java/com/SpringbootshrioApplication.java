package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shendu.springbootshiro.dao")
public class SpringbootshrioApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootshrioApplication.class, args);
    }

}
