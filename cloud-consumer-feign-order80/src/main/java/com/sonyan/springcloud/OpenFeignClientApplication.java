package com.sonyan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author sonyan
 * @create 2022/4/14 15:33
 */
@SpringBootApplication
@EnableFeignClients
public class OpenFeignClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenFeignClientApplication.class,args);
    }
}
