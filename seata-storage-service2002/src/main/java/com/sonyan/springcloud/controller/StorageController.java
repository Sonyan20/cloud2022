package com.sonyan.springcloud.controller;

import com.sonyan.springcloud.bean.CommonResult;
import com.sonyan.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sonyan
 * @create 2022/5/11 10:53
 */
@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count){

        storageService.decrease(productId,count);

        return new CommonResult<>(200, "扣减库存成功！");
    }
}
