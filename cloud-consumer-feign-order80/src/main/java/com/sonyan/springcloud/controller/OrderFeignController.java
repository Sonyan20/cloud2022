package com.sonyan.springcloud.controller;

import com.sonyan.springcloud.entites.Payment;
import com.sonyan.springcloud.service.PaymentFeignService;
import com.sonyan.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sonyan
 * @create 2022/4/14 15:40
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;


    @GetMapping("/consumer/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }


    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openfeign -> ribbon 客户端 一般 默认等待 1s
        return paymentFeignService.paymentFeignTimeout();
    }

}
