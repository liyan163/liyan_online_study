package com.atbaoan.serviceoss.model;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Emma
 * @create 2021-05-09 11:37
 * @description 将配置文件中的数据放到实例中
 */
@Component
public class OssClientConstant implements InitializingBean {
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.file.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.file.bucketName}")
    private String bucketName;


    public static String ENDPOINT;

    public static String ACCESSKEYID;
    public static String ACCESSKEYSECRET;

    public static String BUCKETNAME;



    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
        ACCESSKEYID = accessKeyId;
        ACCESSKEYSECRET = accessKeySecret;
        BUCKETNAME = bucketName;
    }
}
