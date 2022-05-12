package com.sonyan.springcloud.bean;

import lombok.Data;

/**
 * @author sonyan
 * @create 2022/5/11 10:39
 */
@Data
public class Storage {

    private Long id;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 总库存
     */
    private Integer total;
    /**
     * 已用库存
     */
    private Integer used;
    /**
     * 剩余库存
     */
    private Integer residue;
}
