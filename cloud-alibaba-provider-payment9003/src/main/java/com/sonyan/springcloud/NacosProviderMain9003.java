package com.sonyan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sonyan
 * @create 2022/5/9 14:19
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosProviderMain9003 {

    public static void main(String[] args) {
        SpringApplication.run(NacosProviderMain9003.class,args);
    }
}
