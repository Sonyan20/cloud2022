package com.sonyan.springcloud.service;

/**
 * @author sonyan
 * @create 2022/5/11 10:48
 */
public interface StorageService {

    /**
     * 减库存
     */
    void decrease(Long productId, Integer count);
}
