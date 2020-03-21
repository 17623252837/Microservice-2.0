package com.hrh.micro.business.controller;

import com.hrh.micro.commons.dto.com.hrh.ResponseResult;
import com.hrh.micro.provider.api.UmsAdminService;
import com.hrh.micro.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.business.controller
 * @ClassName: RegController
 * @Author: HuRonghua
 * @Description: 注册服务
 * @Date: 2020/3/19 14:31
 * @Version: 1.0
 */

@RestController
@RequestMapping(value = "reg")
public class RegController {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    /**
     * 用户注册.
     * @param umsAdmin {@link UmsAdmin}
     * @return {@link ResponseResult}
     */
    @PostMapping(value = "")
    public ResponseResult<UmsAdmin> Reg(@RequestBody UmsAdmin umsAdmin){
        // 表单验证
        String message = validateReg(umsAdmin);
        // 通过验证
        if (message == null) {
            int insert = umsAdminService.insert(umsAdmin);
            // 注册成功
            if(insert > 0){
                return new ResponseResult<UmsAdmin>(HttpStatus.OK.value(),"用户注册成功",umsAdmin);
            }
        }
        return new ResponseResult<UmsAdmin>(HttpStatus.NOT_ACCEPTABLE.value(),message != null ? message : "用户注册失败");
    }

    /**
     *
     * @param umsAdmin {@link UmsAdmin}
     * @return {@link String}
     */
    private String  validateReg(UmsAdmin umsAdmin){
        UmsAdmin admin = umsAdminService.get(umsAdmin.getUsername());

        if (admin != null) { // 用户名重复
            return  "用户名已存在" ;
        }

        return null;
    }
}
