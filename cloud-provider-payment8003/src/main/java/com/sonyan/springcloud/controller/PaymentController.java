package com.sonyan.springcloud.controller;

import com.sonyan.springcloud.entites.Payment;
import com.sonyan.springcloud.service.PaymentService;
import com.sonyan.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sonyan
 * @create 2022/4/11 11:19
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("[{}]查询结果：{}",port,payment);
        if (payment == null) {
            return new CommonResult<>(400, "["+port+"]查询无此数据，id：" + id);
        }
        return new CommonResult<>(200, "["+port+"]查询成功", payment);
    }

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int res = paymentService.create(payment);
        log.info("[{}]创建结果：{}",port,res);
        return new CommonResult<>(res >= 1 ? 200 : 400, "["+port+"]"+(res >= 1 ? "创建成功" : "创建失败"), res);
    }


    @GetMapping("/payment/lb")
    public String getPort(){
        return port;
    }
}
