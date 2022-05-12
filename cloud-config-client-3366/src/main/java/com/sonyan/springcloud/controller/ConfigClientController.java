package com.sonyan.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sonyan
 * @create 2022/4/20 14:32
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${server.port}")
    private String port;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return "端口号："+ port + "\t 配置信息：" + configInfo;
    }
}
