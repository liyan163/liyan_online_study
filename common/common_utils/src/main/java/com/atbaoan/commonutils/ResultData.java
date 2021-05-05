package com.atbaoan.commonutils;

import lombok.Data;
import org.apache.ibatis.executor.ResultExtractor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Emma
 * @create 2021-02-21 15:13
 * @description 最终统一返回数据（使用链式结构）
 */
@Data
public class ResultData {
    private Boolean success; //是否成功

    private Integer code;  //状态码

    private String message; //提示信息

    private Map<String, Object> data = new HashMap<String, Object>();  //数据

    //构造私有构造器实现单例模式
    private ResultData() {

    }

    //成功
    public static ResultData ok() {
        ResultData resultData = new ResultData();
        resultData.setCode(ResultCode.SUCCESS);
        resultData.setSuccess(true);
        resultData.setMessage("成功");
        return resultData;
    }
    //失败
    public static ResultData error(){
        ResultData resultData = new ResultData();
        resultData.setCode(ResultCode.ERROR);
        resultData.setSuccess(false);
        resultData.setMessage("失败");
        return resultData;
    }

    //链式方程
    public  ResultData success(Boolean success){
        this.success = success;
        return  this;
    }

    public ResultData code(Integer code){
        this.code = code;
        return  this;
    }

    public ResultData message(String message){
        this.message = message;
        return this;
    }

    public ResultData data(Map<String,Object> map){
        this.data = map;
        return this;
    }

    public ResultData data(String key, Object value){
        this.data.put(key,value);
        return this;
    }

}
