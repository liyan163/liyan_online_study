package com.atbaoan.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Emma
 * @create 2021-07-28 22:50
 * @description
 */
@FeignClient("nacos-provider")
public interface FeignClientDemo {
    @GetMapping("/sayHello")
    public String hello();
}
