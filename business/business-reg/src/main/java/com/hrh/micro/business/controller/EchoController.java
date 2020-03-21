package com.hrh.micro.business.controller;

import com.hrh.micro.provider.api.EchoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.business.controller
 * @ClassName: EchoController
 * @Author: HuRonghua
 * @Description: '
 * @Date: 2020/3/19 13:25
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "echo")
public class EchoController {

    @Reference(version = "1.0.0")
    private EchoService echoService;

    @GetMapping(value = "{string}")
    public String echo(@PathVariable  String string){
        return echoService.EchoService(string);
    }
}
