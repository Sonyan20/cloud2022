package com.sonyan.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author sonyan
 * @create 2022/4/13 16:34
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/consul")
    public String paymentConsul(){
        return  "SpringCloud with consul :" + port + "\t" + UUID.randomUUID();
    }
}
