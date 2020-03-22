package com.hrh.micro.business.controller;

import com.hrh.micro.business.dto.IconParam;
import com.hrh.micro.business.dto.profileParam;
import com.hrh.micro.commons.dto.com.hrh.ResponseResult;
import com.hrh.micro.provider.api.UmsAdminService;
import com.hrh.micro.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 获取用户信息
     * @param username 用户名
     * @return
     */
    @GetMapping("/info/{username}")
    public ResponseResult<UmsAdmin> info(@PathVariable String username){
        UmsAdmin umsAdmin = umsAdminService.get(username);
        return new ResponseResult<UmsAdmin>(ResponseResult.CodeStatus.OK,"获取成功",umsAdmin);
    }


    /**
     * 更新用户信息
     * @param param {@link profileParam}
     * @return
     */
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody profileParam param){
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(param,umsAdmin);
        int update = umsAdminService.update(umsAdmin);
        return update > 0 ? new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"更新成功") : new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"更新失败") ;
    }


    /**
     * 修改头像
     * @param iconParam {@link IconParam}
     * @return
     */
    @PostMapping(value = "/modify/icon")
    public ResponseResult<Void> modifyIcon(@RequestBody IconParam iconParam) {
        System.out.println(iconParam);
        int result = umsAdminService.modifyIcon(iconParam.getUsername(), iconParam.getPath());
        return  result > 0 ? new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "更新头像成功") : new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "更新头像失败");
    }


}
