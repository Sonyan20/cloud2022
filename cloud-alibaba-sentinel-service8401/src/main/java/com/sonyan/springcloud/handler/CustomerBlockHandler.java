package com.sonyan.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sonyan.springcloud.vo.CommonResult;

/**
 * @author sonyan
 * @create 2022/5/9 13:56
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception){
        return new CommonResult<>(444,"按客户自定义, global handlerException-------1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult<>(444,"按客户自定义, global handlerException-------2");
    }
}
