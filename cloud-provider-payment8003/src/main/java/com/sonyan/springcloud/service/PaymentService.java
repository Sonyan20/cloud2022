package com.sonyan.springcloud.service;

import com.sonyan.springcloud.entites.Payment;

/**
 * @author sonyan
 * @create 2022/4/11 11:14
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
