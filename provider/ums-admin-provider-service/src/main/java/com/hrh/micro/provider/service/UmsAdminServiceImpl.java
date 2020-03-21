package com.hrh.micro.provider.service;

import com.hrh.micro.provider.api.UmsAdminService;
import com.hrh.micro.provider.domain.UmsAdmin;
import com.hrh.micro.provider.mapper.UmsAdminMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.provider.service
 * @ClassName: UmsAdminServiceImpl
 * @Author: HuRonghua
 * @Description: impl
 * @Date: 2020/3/19 13:50
 * @Version: 1.0
 */

@Service(version = "1.0.0")
public class UmsAdminServiceImpl implements UmsAdminService {

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public int insert(UmsAdmin umsAdmin) {
        // 初始化用户对象
        initUmsAdmin(umsAdmin);
        return umsAdminMapper.insert(umsAdmin);
    }

    @Override
    public UmsAdmin get(String userName) {
        Example example = new Example(UmsAdmin.class);
        example.createCriteria().andEqualTo("username",userName);
        return umsAdminMapper.selectOneByExample(example);
    }

    /**
     * 初始化UmsAdmin
     * @param umsAdmin {@link UmsAdmin}
     */
    private void initUmsAdmin(UmsAdmin umsAdmin){
        // 创建时间，最后登陆时间
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setLoginTime(new Date());
        // 初始化状态 0 : 封禁
        if (umsAdmin.getStatus() == null) {
            umsAdmin.setStatus(0);
        }
        // 密码加密
        umsAdmin.setPassword(bCryptPasswordEncoder.encode(umsAdmin.getPassword()));
    }
}
