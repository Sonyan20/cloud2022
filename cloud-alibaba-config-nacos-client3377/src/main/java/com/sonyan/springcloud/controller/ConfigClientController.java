package com.sonyan.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sonyan
 * @create 2022/4/26 11:01
 */
@RestController
@RefreshScope //控制器类加入 @RefreshScope 注解使当前类下的配置支持 Nacos 的动态刷新功能。
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping( "/config/info" )
    public String getConfigInfo() {
        return configInfo ;
    }
}
