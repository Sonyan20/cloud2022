package com.sonyan.springcloud.service.impl;

import com.sonyan.springcloud.service.IMessageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author sonyan
 * @create 2022/4/21 10:00
 */
@EnableBinding(Source.class)  //定义消息推送的管道
public class MessageProviderImpl implements IMessageProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProviderImpl.class);

    @Resource
    private MessageChannel output;  //消息发送管道

    @Override
    public String send() {
        String value = UUID.randomUUID().toString();
        boolean send = output.send(MessageBuilder.withPayload(value).build());
        LOGGER.info(">>>>>> value:{},send:{}",value,send);
        return  send ? "发送成功" : "发送失败";
    }
}
