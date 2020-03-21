package com.hrh.micro.provider.service;

import com.hrh.micro.provider.api.EchoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.provider.service
 * @ClassName: EchoServiceimp
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/3/19 12:32
 * @Version: 1.0
 */
@Service(version = "1.0.0")
public class EchoServiceImpl implements EchoService {

    @Override
    public String EchoService(String string) {
        return "Hello Dubbo" + string;
    }
}
