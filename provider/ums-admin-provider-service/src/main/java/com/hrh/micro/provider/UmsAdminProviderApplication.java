package com.hrh.micro.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.provider
 * @ClassName: UmsAdminProviderApplication
 * @Author: HuRonghua
 * @Description: 入口类
 * @Date: 2020/3/19 12:29
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.hrh.micro.provider.mapper")
public class UmsAdminProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(UmsAdminProviderApplication.class,args);
    }
}
