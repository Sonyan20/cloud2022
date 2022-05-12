package com.sonyan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sonyan
 * @create 2022/4/22 15:26
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderMain9001 {

    public static void main(String[] args) {
        SpringApplication.run(NacosProviderMain9001.class,args);
    }
}
