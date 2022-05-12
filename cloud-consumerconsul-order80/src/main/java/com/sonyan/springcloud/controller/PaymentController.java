package com.sonyan.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author sonyan
 * @create 2022/4/13 14:17
 */
@RestController
public class PaymentController {

    private static final String PAYMENT_URL = "http://cloud-consul-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String getPaymentZk(){
        String url = PAYMENT_URL + "/payment/consul";
        return this.restTemplate.getForObject(url,String.class);
    }
}
