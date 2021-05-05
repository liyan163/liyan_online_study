package com.atbaoan.commonutils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Emma
 * @create 2021-04-15 23:31
 * @description
 */
@Data
@ApiModel(value = "统一返回实体")
public class R {
    @ApiModelProperty(name = "是否成功状态为位")
    private Boolean success;
    @ApiModelProperty(name = "状态码")
    private Integer code;
    @ApiModelProperty(name = "返回描述信息")
    private String message;
    @ApiModelProperty(name = "返回值数据")
    private Map<String,Object> data = new HashMap<>();

    //私有化构造器
    private R(){
    }

    //返回成功的静态方法
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setMessage("成功");
        r.setCode(ResultCode.SUCCESS);
        return r;
    }
    //返回失败的静态方法
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setMessage("失败");
        r.setCode(ResultCode.ERROR);
        return r;
    }

    //链式编程
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(Map<String,Object> map){
        this.setData(map);
        return this;
    }

    public R data(String key,Object value){
        this.data.put(key,value);
        return this;
    }
}