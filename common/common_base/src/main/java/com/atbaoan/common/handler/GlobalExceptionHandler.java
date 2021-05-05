package com.atbaoan.common.handler;

import com.atbaoan.commonutils.ResultData;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Emma
 * @create 2021-02-22 20:32
 * @description 定义全局异常处理器
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultData error(Exception e) {
        e.printStackTrace();
        log.debug("execute global exception e:{}", e);
        return ResultData.error().message("执行力全局异常");

    }
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResultData error(ArithmeticException e) {
        log.debug("execute arithmetic exception e:{}",e);
        e.printStackTrace();
        return ResultData.error().message("执行了算数异常处理");
    }

    @ExceptionHandler(value = EmmaException.class)
    @ResponseBody
    public ResultData error(EmmaException e){
        log.debug("execute emma exception e:{}",e);
        e.printStackTrace();
        return ResultData.error().message("执行了自定义异常");
    }
}
