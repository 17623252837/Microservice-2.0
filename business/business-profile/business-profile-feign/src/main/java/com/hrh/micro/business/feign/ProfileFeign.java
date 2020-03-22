package com.hrh.micro.business.feign;

import com.hrh.micro.Configuration.FeignRequestConfiguration;
import com.hrh.micro.business.dto.IconParam;
import com.hrh.micro.business.dto.profileParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    /**
     * 获取用户信息
     * @param username 用户名
     * @return
     */
    @GetMapping(value = "/info/{username}")
    String info(@PathVariable String username);

    /**
     * 更新用户信息
     * @param profileParam {@link profileParam}
     * @return
     */
    @PostMapping(value = "/update")
    String update(@RequestBody profileParam profileParam);


    /**
     * 更新用户头像
     * @param iconParam {@link IconParam}
     * @return
     */
    @PostMapping(value = "/modify/icon")
    String modifyIcon(@RequestBody IconParam iconParam);
}
