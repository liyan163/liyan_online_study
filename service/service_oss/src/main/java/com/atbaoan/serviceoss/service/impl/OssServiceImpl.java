package com.atbaoan.serviceoss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.internal.OSSConstants;
import com.aliyun.oss.model.OSSObject;
import com.atbaoan.common.handler.EmmaException;
import com.atbaoan.common.model.ExceptionEnum;
import com.atbaoan.serviceoss.model.OssClientConstant;
import com.atbaoan.serviceoss.service.OssService;
import org.apache.tomcat.util.net.openssl.ciphers.OpenSSLCipherConfigurationParser;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @author Emma
 * @create 2021-05-09 11:52
 * @description 阿里云对象存储服务类
 */
@Service
public class OssServiceImpl  implements OssService {


    //上传文件
    @Override
    public String uploadFile(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = OssClientConstant.ENDPOINT;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = OssClientConstant.ACCESSKEYID;
        String accessKeySecret = OssClientConstant.ACCESSKEYSECRET;
        String bucketName = OssClientConstant.BUCKETNAME;
// <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String filename = file.getOriginalFilename();

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。

        try {
            //使用uuid保证文件名不重名
            filename = UUID.randomUUID()+filename;
            //使用日期将文件按照日期进行分类
            filename = new DateTime().toString("yyyy/MM/dd") + filename;
            ossClient.putObject(bucketName, filename, new ByteArrayInputStream(file.getBytes()));
            return "https://"+bucketName+"."+endpoint+"/"+filename;
        } catch (Exception e) {
            throw new EmmaException(ExceptionEnum.UPLOADFILE_ERROR.getCode(),ExceptionEnum.UPLOADFILE_ERROR.getMessage());
        }finally {

            // 关闭OSSClient。
            ossClient.shutdown();
        }

    }

    @Override
    public void downLoad(String file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = OssClientConstant.ENDPOINT;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = OssClientConstant.ACCESSKEYID;
        String accessKeySecret = OssClientConstant.ACCESSKEYSECRET;
        String bucketName = OssClientConstant.BUCKETNAME;

// <yourObjectName>从OSS下载文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, file);
// 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();
        if (content != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            while (true) {
                String line = null;
                try {
                    line = reader.readLine();
                    if (line == null) break;
                    System.out.println("\n" + line);
                } catch (Exception e) {
                    throw new EmmaException(ExceptionEnum.DOWNLOAD_ERROR.getCode(),ExceptionEnum.DOWNLOAD_ERROR.getMessage());
                }finally {
                    // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
                    try {
                        content.close();
                        // 关闭OSSClient。
                        ossClient.shutdown();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }

    }
}
