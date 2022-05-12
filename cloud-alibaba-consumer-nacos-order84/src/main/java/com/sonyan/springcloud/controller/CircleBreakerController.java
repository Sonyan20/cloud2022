package com.sonyan.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sonyan.springcloud.entites.Payment;
import com.sonyan.springcloud.service.PaymentService;
import com.sonyan.springcloud.vo.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author sonyan
 * @create 2022/4/22 16:06
 */
@RestController
public class CircleBreakerController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;


    @GetMapping("/consumer/fallback/{id}")
//    @SentinelResource("fallback") //无配置
//    @SentinelResource(value = "fallback",fallback = "handlerFallback") //fallback 只负责业务异常
//    @SentinelResource(value = "fallback",blockHandler = "blockHandler") //blockHandler 只负责sentinel 控制台配置违规
    @SentinelResource(value = "fallback",
            fallback = "handlerFallback",
            blockHandler = "blockHandler",exceptionsToIgnore = {IllegalArgumentException.class}) //
    public CommonResult<?> getPayment(@PathVariable("id") Integer id){
        CommonResult result = restTemplate.getForObject(serverUrl + "/payment/" + id, CommonResult.class, id);
        if (id <= 0){
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常.....");
        }else if (result.getData() == null){
            throw new NullPointerException("NullPointerException,该ID没有对应的参数,空指针异常");
        }
        return result;
    }

    public CommonResult handlerFallback(@PathVariable Integer id, Throwable e){
        return new CommonResult(444,"兜底异常handlerFallback，exception："+e.getMessage(), new Payment(Long.valueOf(id),null));
    }

    public CommonResult blockHandler(@PathVariable Integer id, BlockException blockException){
        return new CommonResult(445,"blockHandler-sentinel 限流，无此流水，BlockException："+ blockException.getMessage(), new Payment(Long.valueOf(id),null));
    }


    /**
     * openFeign 测试
     */
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/openfeign/{id}")
    public CommonResult queryById(@PathVariable("id") Integer id){
        return paymentService.queryById(id);
    }
}
