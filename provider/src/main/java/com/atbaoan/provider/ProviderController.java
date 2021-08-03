package com.atbaoan.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Emma
 * @create 2021-07-26 22:58
 * @description
 */
@RestController
public class ProviderController {
    @Value("${liyan}")
    private String name;

    @GetMapping("/sayHello")
    public String hello(){
        return "hello" + name;
    }
}
