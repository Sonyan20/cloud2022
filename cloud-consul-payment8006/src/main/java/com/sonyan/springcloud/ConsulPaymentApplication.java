package com.sonyan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sonyan
 * @create 2022/4/13 16:33
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsulPaymentApplication.class,args);
    }
}
