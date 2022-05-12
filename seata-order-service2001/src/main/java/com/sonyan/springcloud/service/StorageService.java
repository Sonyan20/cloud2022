package com.sonyan.springcloud.service;

import com.sonyan.springcloud.bean.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author sonyan
 * @create 2022/5/10 16:51
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {

    /**
     * 减扣库存
     */
    @PostMapping("/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
