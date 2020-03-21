package com.hrh.micro.business.controller;

import com.hrh.micro.commons.dto.com.hrh.ResponseResult;
import com.hrh.micro.provider.api.UmsAdminService;
import com.hrh.micro.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.business.controller
 * @ClassName: ProfileController
 * @Author: HuRonghua
 * @Description: 个人信息管理
 * @Date: 2020/3/21 6:22
 * @Version: 1.0
 */
@RestController
@RequestMapping("profile")
public class ProfileController {
    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @GetMapping("/info/{username}")
    public ResponseResult<UmsAdmin> info(@PathVariable String username){
        UmsAdmin umsAdmin = umsAdminService.get(username);
        return new ResponseResult<UmsAdmin>(ResponseResult.CodeStatus.OK,"获取成功",umsAdmin);
    }
}
