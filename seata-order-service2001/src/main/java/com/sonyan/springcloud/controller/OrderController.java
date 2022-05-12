package com.sonyan.springcloud.controller;

import com.sonyan.springcloud.bean.CommonResult;
import com.sonyan.springcloud.bean.Order;
import com.sonyan.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sonyan
 * @create 2022/5/10 16:33
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult createOrder(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
