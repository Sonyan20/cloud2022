package com.sonyan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sonyan
 * @create 2022/5/9 14:20
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosProviderMain9004 {

    public static void main(String[] args) {
        SpringApplication.run(NacosProviderMain9004.class,args);
    }
}
