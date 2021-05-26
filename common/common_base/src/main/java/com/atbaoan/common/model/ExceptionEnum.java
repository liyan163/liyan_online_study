package com.atbaoan.common.model;

import lombok.Data;

/**
 * @author Emma
 * @create 2021-05-09 20:58
 * @description 异常枚举类
 */
public enum  ExceptionEnum {

    //   ----------------- 文件上传异常---------
    UPLOADFILE_ERROR(1,"文件上传异常"),
    DOWNLOAD_ERROR(1,"文件上传异常"),
    ;
    private Integer code;
    private String message;
    ExceptionEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public void setCode(Integer code){
        this.code = code;
    }

    public Integer getCode(){
        return this.code;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public String getMessageByCode(Integer code){

        return "";
    }
}

