package com.hrh.micro.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.business.dto
 * @ClassName: IconParam
 * @Author: HuRonghua
 * @Description: 头像更改传输对象
 * @Date: 2020/3/22 16:12
 * @Version: 1.0
 */
@Data
public class IconParam implements Serializable {
    /**
     *  用户名
     */
    private String username;


    /**
     * 图片路径
     */
    private  String path;
}
