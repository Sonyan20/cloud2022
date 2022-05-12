package com.sonyan.springcloud.controller;

import com.sonyan.springcloud.entites.Payment;
import com.sonyan.springcloud.vo.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author sonyan
 * @create 2022/5/9 14:28
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String port;

    public static HashMap<Integer, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1,new Payment(1L, "c936e38ffa4042bf9045a9196b6e5622"));
        hashMap.put(2,new Payment(2L, "f841094b0fea4e12bf2be67577ae2ab8"));
        hashMap.put(3,new Payment(3L, "1fc72a29aa31496fa9da06980bf46ab8"));
        hashMap.put(4,new Payment(4L, "033dc537882e4279990ca02104732f67"));
        hashMap.put(5,new Payment(5L, "48bddbc60ae14389b7a8aadce84ed9de"));
    }


    @GetMapping("/payment/{id}")
    public CommonResult<?> queryById(@PathVariable("id") Integer id){
        Payment payment = hashMap.get(id);
        return new CommonResult<>(200,"查询成功，server.port="+ port, payment);
    }
}
