package com.sonyan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sonyan
 * @create 2022/4/13 13:35
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用 consul 和 zookeeper 作为注册中心时注册服务
public class PaymentApplication4 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication4.class,args);
    }
}
