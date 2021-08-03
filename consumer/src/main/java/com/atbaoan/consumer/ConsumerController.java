package com.atbaoan.consumer;

import com.atbaoan.consumer.feign.FeignClientDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Emma
 * @create 2021-07-26 22:59
 * @description
 */
@RestController
@RefreshScope
public class ConsumerController {
    @Value("${liyan}")
    private String name;
    @Value("${redis}")
    private String redis;
    @Value("${mysql}")
    private String mysql;
    @Autowired
    private FeignClientDemo feignClientDemo;

    @GetMapping("/sayHi")
    public String hello(){
        return "hi" + "-name" +name + "-redis" +redis + "-mysql" +mysql ;
    }

    @GetMapping("/sayHello2")
    public String hello2(){
        return feignClientDemo.hello();
    }
}
