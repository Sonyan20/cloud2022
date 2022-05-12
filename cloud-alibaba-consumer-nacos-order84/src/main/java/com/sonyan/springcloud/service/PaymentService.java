package com.sonyan.springcloud.service;

import com.sonyan.springcloud.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 使用  fallback 方式是无法获取异常信息的，
 * 如果想要获取异常信息，可以使用 fallbackFactory 参数
 * @author sonyan
 * @create 2022/5/9 15:56
 */
@FeignClient(value = "nacos-payment-provider",fallback  = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping("/payment/{id}")
    public CommonResult<?> queryById(@PathVariable( "id" ) Integer id);
}
