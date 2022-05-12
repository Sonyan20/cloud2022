package com.sonyan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author sonyan
 * @create 2022/4/12 10:22
 */
@SpringBootApplication
@EnableEurekaClient
public class PaymentApplication2 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication2.class,args);
    }
}
