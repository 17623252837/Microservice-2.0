package com.hrh.micro.business.service;

import com.google.common.collect.Lists;
import com.hrh.micro.provider.api.UmsAdminService;
import com.hrh.micro.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.business.service
 * @ClassName: UserDetailsServiceImpl
 * @Author: HuRonghua
 * @Description: 自定义认证授权
 * @Date: 2020/3/20 22:41
 * @Version: 1.0
 */

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "$2a$10$WhCuqmyCsYdqtJvM0/J4seCU.xZQHe2snNE5VFUuBGUZWPbtdl3GG";

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 给每个用户USER权限
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        GrantedAuthority grantedAuthority =new SimpleGrantedAuthority("USER");
        grantedAuthorities.add(grantedAuthority);
        //
        UmsAdmin umsAdmin = umsAdminService.get(s);
        // 账号存在
        if (umsAdmin != null) {
            return new User(umsAdmin.getUsername(),umsAdmin.getPassword(),grantedAuthorities);
        }
        return null;
    }
}
