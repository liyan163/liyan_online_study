package com.atbaoan.serviceedu;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 * @create 2021-02-17 18:27
 */
@SpringBootApplication
@ComponentScan("com.atbaoan")
@MapperScan("com.atbaoan.serviceedu.mapper")
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
