package com.sonyan.springcloud.service;

import org.springframework.stereotype.Service;

/**
 * @author sonyan
 * @create 2022/4/18 10:46
 */
@Service
public class PaymentFallbackService implements PaymentHystrixService{


    @Override
    public String paymentInfo_OK(Integer id) {
        return "=== PaymentFallbackService --paymentInfo_OK-- fall back, 😭";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "=== PaymentFallbackService --paymentInfo_Timeout-- fall back, 😭";
    }
}
