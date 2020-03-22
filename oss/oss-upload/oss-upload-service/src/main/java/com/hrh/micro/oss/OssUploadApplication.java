package com.hrh.micro.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.oss
 * @ClassName: OssUploadApplication
 * @Author: HuRonghua
 * @Description: o's's 云存储服务入口类
 * @Date: 2020/3/22 14:50
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OssUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssUploadApplication.class,args);
    }
}
