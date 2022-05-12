package com.sonyan.springcloud.service.impl;

import com.sonyan.springcloud.mapper.StorageMapper;
import com.sonyan.springcloud.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sonyan
 * @create 2022/5/11 10:49
 */
@Service
public class StorageServiceImpl implements StorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageMapper storageMapper;

    /**
     * 扣减库存
     */
    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("[storage-service] 开始 扣减库存");
        storageMapper.decrease(productId,count);
        LOGGER.info("[storage-service] 完成 扣减库存");
    }
}
