package com.hrh.micro.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.business
 * @ClassName: BusinessProfileApplication
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/3/21 6:18
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BusinessProfileApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessProfileApplication.class,args);
    }
}
