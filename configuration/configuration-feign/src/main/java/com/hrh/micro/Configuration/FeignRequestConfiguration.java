package com.hrh.micro.Configuration;

import com.hrh.micro.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.Configuration
 * @ClassName: FeignConfigureation
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/3/21 7:19
 * @Version: 1.0
 */
@Configuration
public class FeignRequestConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignRequestInterceptor();
    }
}
