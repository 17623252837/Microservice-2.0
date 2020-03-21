package com.hrh.micro.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.business
 * @ClassName: BusinessOAuth2Application
 * @Author: HuRonghua
 * @Description: 验证服务入口类
 * @Date: 2020/3/20 22:05
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BusinessOAuth2Application {
    public static void main(String[] args) {
        SpringApplication.run(BusinessOAuth2Application.class,args);
    }
}
