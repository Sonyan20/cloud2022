package com.sonyan.springcloud.service.impl;

import com.sonyan.springcloud.mapper.AccountMapper;
import com.sonyan.springcloud.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author sonyan
 * @create 2022/5/11 11:15
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountMapper accountMapper;

    /**
     * 扣减账户余额
     *
     */
    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("[account-service] 开始 扣减账户余额");
        //模拟 异常超时，全局事物回滚 暂停几秒 线程
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountMapper.decrease(userId, money);
        LOGGER.info("[account-service] 完成 扣减账户余额");
    }
}
