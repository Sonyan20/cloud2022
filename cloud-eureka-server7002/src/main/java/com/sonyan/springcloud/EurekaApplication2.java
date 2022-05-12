package com.sonyan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author sonyan
 * @create 2022/4/11 16:33
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication2 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication2.class,args);
    }
}
