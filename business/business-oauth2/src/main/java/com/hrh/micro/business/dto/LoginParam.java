package com.hrh.micro.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.business.dto
 * @ClassName: LoginParam
 * @Author: HuRonghua
 * @Description: 登录参数
 * @Date: 2020/3/20 23:22
 * @Version: 1.0
 */

@Data
public class LoginParam implements Serializable {

    private String username;
    private String password;
}
