package com.sonyan.springcloud.service.impl;

import com.sonyan.springcloud.bean.Order;
import com.sonyan.springcloud.mapper.OrderMapper;
import com.sonyan.springcloud.service.AccountService;
import com.sonyan.springcloud.service.OrderService;
import com.sonyan.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sonyan
 * @create 2022/5/10 16:49
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    /**
     * 创建订单 -> 调用库存服务 减扣库存 -> 调用账户服务 扣减账户余额 -> 修改订单状态
     * (下订单 -> 减库存 -> 减余额 -> 该状态)
     */
    @Override
    @GlobalTransactional(name = "tx-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("----------开始下单---------");
        log.info("==========开始创建订单=========");
        orderMapper.create(order); //创建订单
        log.info("==========订单创建完成=========");

        //远程调用扣减 库存
        log.info("[order-server]开始 扣减库存。。。");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("[order-server]完成 扣减库存。。。");

        //调用账户服务 扣减账户余额
        log.info("[order-server]开始 扣减账户余额。。。");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("[order-server]完成 扣减账户余额。。。");

        //修改订单状态
        log.info("[order-server]开始 修改订单状态。。。");
        orderMapper.update(order.getUserId(), 0);
        log.info("[order-server]完成 修改订单状态。。。");


        log.info("----------下单结束---------");

    }
}
