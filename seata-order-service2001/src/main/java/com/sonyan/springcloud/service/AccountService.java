package com.sonyan.springcloud.service;

import com.sonyan.springcloud.bean.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author sonyan
 * @create 2022/5/11 10:01
 */
@FeignClient(value = "seata-account-service")
public interface AccountService {

    /**
     * 减扣账户余额
     */
    @PostMapping("/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money")BigDecimal money);
}
