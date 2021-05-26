package com.atbaoan.serviceoss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Emma
 * @create 2021-05-09 11:52
 * @description
 */
public interface OssService {
    //上传文件
    String uploadFile(MultipartFile file);

    void downLoad(String file);
}
