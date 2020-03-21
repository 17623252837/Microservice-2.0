package com.hrh.micro.provider.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.provider.configure
 * @ClassName: UmsAdminResourceConfiguration
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/3/19 14:06
 * @Version: 1.0
 */

@Configuration
public class UmsAdminResourceConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
