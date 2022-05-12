package com.sonyan.springcloud.controller;

import com.sonyan.springcloud.entites.Payment;
import com.sonyan.springcloud.lb.MyLoadBalancer;
import com.sonyan.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sonyan
 * @create 2022/4/11 14:03
 */
@RestController
@Slf4j
public class PaymentController {

    private static final String SINGLE_PAYMENT_URL = "http://localhost:8001";
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private MyLoadBalancer myLoadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        List<ServiceInstance> instances = this.discoveryClient.getInstances("cloud-payment-service");
        if (instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance instance = myLoadBalancer.instances(instances);
        String url = instance.getUri() + "/payment/get/"+id;
       // String url = PAYMENT_URL + "/payment/get/"+id;
        return this.restTemplate.getForObject(url, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult getPaymentById2(@PathVariable("id") Long id){
        String url = PAYMENT_URL + "/payment/get/"+ id;
        ResponseEntity<CommonResult> entity = this.restTemplate.getForEntity(url, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult(400,"操作失败");
        }
//        return this.restTemplate.getForObject(url, CommonResult.class);
    }

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
        String url = PAYMENT_URL + "/payment/create";
        return this.restTemplate.postForObject(url, payment, CommonResult.class);
    }


    @GetMapping("/consumer/payment/lb")
    public String getPaymentLoadBalancer(){
        List<ServiceInstance> instances = this.discoveryClient.getInstances("cloud-payment-service");
        if (instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance instance = myLoadBalancer.instances(instances);
        return this.restTemplate.getForObject(instance.getUri() + "/payment/lb",String.class);
    }

    // ========== zipkin + sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        return restTemplate.getForObject("http://localhost:8001"+ "/payment/zipkin",String.class);
    }
}
