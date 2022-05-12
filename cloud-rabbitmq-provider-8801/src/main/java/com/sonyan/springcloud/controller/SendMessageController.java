package com.sonyan.springcloud.controller;

import com.sonyan.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sonyan
 * @create 2022/4/21 10:14
 */
@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider messageProvider;

    @GetMapping("/send")
    public String send(){
        return messageProvider.send();
    }
}
