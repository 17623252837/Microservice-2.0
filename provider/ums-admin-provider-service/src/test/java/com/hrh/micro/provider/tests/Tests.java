package com.hrh.micro.provider.tests;
import java.util.Date;

import com.hrh.micro.provider.api.UmsAdminService;
import com.hrh.micro.provider.domain.UmsAdmin;
import com.hrh.micro.provider.mapper.UmsAdminMapper;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.provider.tests
 * @ClassName: Tests
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/3/19 13:02
 * @Version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Tests {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testSelectAll(){
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectAll();

        System.out.println(umsAdmins);
    }


    @Test
    public void testInsert(){
        UmsAdmin umsAdmins = new UmsAdmin();
        umsAdmins.setId(111112L);
        umsAdmins.setUsername("123");
        umsAdmins.setPassword(passwordEncoder.encode("123456"));
        umsAdmins.setIcon("123");
        umsAdmins.setEmail("123");
        umsAdmins.setNickName("123");
        umsAdmins.setNote("123");
        umsAdmins.setCreateTime(new Date());
        umsAdmins.setLoginTime(new Date());
        umsAdmins.setStatus(0);

        int insert = umsAdminService.insert(umsAdmins);
        System.out.println(insert);

    }
}
