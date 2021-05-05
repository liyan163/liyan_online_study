package com.atbaoan.common.handler;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.IntrospectionException;

/**
 * @author Emma
 * @create 2021-02-22 20:27
 * @description 自定义异常类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmmaException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "状态信息")
    private String message;
}
