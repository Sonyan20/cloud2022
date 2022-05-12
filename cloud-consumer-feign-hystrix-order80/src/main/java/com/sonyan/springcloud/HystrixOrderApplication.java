package com.sonyan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author sonyan
 * @create 2022/4/15 13:18
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class HystrixOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixOrderApplication.class,args);
    }
}
