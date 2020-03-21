package com.hrh.micro.business.feign;

import com.hrh.micro.Configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.feign
 * @ClassName: ProfileFeign
 * @Author: HuRonghua
 * @Description: 用户信息接口
 * @Date: 2020/3/21 6:28
 * @Version: 1.0
 */
@FeignClient(value = "business-profile",path = "profile",configuration = FeignRequestConfiguration.class)
public interface ProfileFeign {

    @GetMapping(value = "/info/{username}")
    String info(@PathVariable String username);


}
