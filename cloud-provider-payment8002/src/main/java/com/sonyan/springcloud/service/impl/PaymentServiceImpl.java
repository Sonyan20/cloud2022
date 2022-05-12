package com.sonyan.springcloud.service.impl;

import com.sonyan.springcloud.dao.PaymentDao;
import com.sonyan.springcloud.entites.Payment;
import com.sonyan.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sonyan
 * @create 2022/4/11 11:15
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;


    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }
}
