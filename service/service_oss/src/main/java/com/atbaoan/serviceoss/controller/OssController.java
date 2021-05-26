package com.atbaoan.serviceoss.controller;

import com.atbaoan.commonutils.R;
import com.atbaoan.serviceoss.service.OssService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Emma
 * @create 2021-05-09 11:52
 * @description 阿里云对象存储oss管理
 */
@RestController
@RequestMapping("/serviceoss")
@Api(value = "阿里云对象存储管理类")
@CrossOrigin
public class OssController {
    @Autowired
    private  OssService ossService;
    //文件上传接口
    @PostMapping("uploadFile")
    public R uploadFile(MultipartFile file){
        String url = ossService.uploadFile(file);
        return R.ok().data("url",url);
    }


    //文件下载接口
    @PostMapping("downLoad")
    public R downLoad(String file){
        ossService.downLoad(file);
        return R.ok();
    }
}
