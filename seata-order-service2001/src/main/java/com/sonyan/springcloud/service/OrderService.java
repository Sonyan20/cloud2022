package com.sonyan.springcloud.service;

import com.sonyan.springcloud.bean.Order;

/**
 * @author sonyan
 * @create 2022/5/10 16:48
 */
public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}
