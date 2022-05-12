package com.sonyan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author sonyan
 * @create 2022/5/9 14:32
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class NacosOrderMain84 {

    public static void main(String[] args) {
        SpringApplication.run(NacosOrderMain84.class,args);
    }

}
