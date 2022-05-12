package com.sonyan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sonyan
 * @create 2022/4/22 15:48
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosProviderMain9002 {

    public static void main(String[] args) {
        SpringApplication.run(NacosProviderMain9002.class,args);
    }
}
