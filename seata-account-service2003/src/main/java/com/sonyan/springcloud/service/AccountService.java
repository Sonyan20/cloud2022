package com.sonyan.springcloud.service;

import java.math.BigDecimal;

/**
 * @author sonyan
 * @create 2022/5/11 11:14
 */
public interface AccountService {
    /**
     * 扣减账户余额
     */
    void decrease( Long userId, BigDecimal money);
}
