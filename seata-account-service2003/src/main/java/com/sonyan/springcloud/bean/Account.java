package com.sonyan.springcloud.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author sonyan
 * @create 2022/5/11 11:01
 */
@Data
public class Account {

    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 总金额
     */
    private BigDecimal total;
    /**
     * 已用额度
     */
    private BigDecimal used;
    /**
     * 剩余额度
     */
    private BigDecimal residue;
}
