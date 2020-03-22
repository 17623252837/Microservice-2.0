package com.hrh.micro.provider.api;

import com.hrh.micro.provider.domain.UmsAdmin;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.provider.api
 * @ClassName: UmsAdminService
 * @Author: HuRonghua
 * @Description: 用户接口服务
 * @Date: 2020/3/19 13:49
 * @Version: 1.0
 */
public interface UmsAdminService {
    /**
     * 新增用户 大于0 增加成功
     * @param umsAdmin {@link UmsAdmin}
     * @return
     */
    int insert(UmsAdmin umsAdmin);

    /**
     *根据用户名获取用户
     *
     * @param userName 用户名
     * @return {@link UmsAdmin}
     */
    UmsAdmin get(String userName);

    /**
     * 更新用户
     *
     * @param umsAdmin {@link UmsAdmin}
     * @return
     */
    int update(UmsAdmin umsAdmin);


    /**
     * 跟新用户头像
     * @param username 用户名
     * @param path 图片路径
     * @return
     */
    int modifyIcon(String username, String path);
}
