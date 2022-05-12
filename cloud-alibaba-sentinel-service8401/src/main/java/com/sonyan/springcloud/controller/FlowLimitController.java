package com.sonyan.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sonyan
 * @create 2022/5/7 10:22
 */
@RestController
@Slf4j
public class FlowLimitController {


    @GetMapping("/testA")
    public String testA(){
        return "-------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "-------testB";
    }

    @GetMapping("/testD")
    public String testD(){
//        try {
//            //暂停 1 秒钟线程
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info("testD 测试RT");
        log.info("testD 测试异常比例");
        int i = 10 / 0;
        return "-------testD";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        /*
            会出现 error page 。是因为 @sentinelResource 是处理 控制台配置的违规情况，有 blockException 方法配置 兜底处理；
            而 （int a = 10/0;）异常 是 java运行时异常 RuntimeException ，@SentinelResource 是不会进行处理
         */
        //int a = 10/0; //测试异常报错
        return "-------testHotKey,p1: {" + p1 + "} ,p2: {" + p2 + "}";
    }


    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "-------deal_testHotKey o(T^T)o";
    }
}
