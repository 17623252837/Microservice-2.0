package com.hrh.micro.business.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.business.dto
 * @ClassName: profileParam
 * @Author: HuRonghua
 * @Description: 个人信息传输对象
 * @Date: 2020/3/21 23:41
 * @Version: 1.0
 */
@Data
public class profileParam implements Serializable {
    private static final long serialVersionUID = 2923994852116783195L;

    private Long id;

    private String username;

    private String password;

    /**
     * 头像
     */
    private String icon;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后登录时间
     */
    private Date loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private Integer status;

}
