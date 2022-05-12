package com.sonyan.springcloud.controller;

import com.sonyan.springcloud.entites.Payment;
import com.sonyan.springcloud.service.PaymentService;
import com.sonyan.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private DiscoveryClient discoveryClient;

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
        int i = paymentService.create(payment);
        log.info("[{}]创建结果：{}",port,i);
        return new CommonResult<>(i >= 1 ? 200 : 400, "["+port+"]"+(i >= 1 ? "创建成功" : "创建失败"), i);
    }



    @GetMapping("/payment/discovery")
    public Object getDiscoveryInfo(){
        String description = discoveryClient.description();
        List<String> services = discoveryClient.getServices();
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        Map<String, Object> map = new HashMap<>();

        map.put("description",description);
        map.put("services",services);
        map.put("instances",instances);

        return map;
    }


    @GetMapping("/payment/lb")
    public String getPort(){
        return port;
    }


    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }


    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "Hi, I am paymentZipkin server fall back. Welcome to access this interface!";
    }
}
