package com.sonyan.springcloud.service;

import com.sonyan.springcloud.entites.Payment;
import com.sonyan.springcloud.vo.CommonResult;
import org.springframework.stereotype.Component;

/**
 * @author sonyan
 * @create 2022/5/9 15:59
 */
@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult queryById(Integer id) {
        return new CommonResult(4444 , "PaymentFallbackService: 服务降级返回 , 没有该流水信息" , new Payment(Long.valueOf(id), "errorSerial......" ));
    }
}
