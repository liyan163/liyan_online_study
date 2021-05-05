package com.atbaoan.serviceedu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Emma
 * @create 2021-02-17 18:34
 */

@Configuration
public class EduConfig {
    //2. 用于逻辑删除的插件
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }


    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
