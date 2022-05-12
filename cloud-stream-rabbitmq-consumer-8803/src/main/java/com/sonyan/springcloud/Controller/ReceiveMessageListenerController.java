package com.sonyan.springcloud.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author sonyan
 * @create 2022/4/21 10:59
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveMessageListenerController.class);

    @Value("${server.port}")
    private String port;


    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        LOGGER.info("消费者No.2 ,端口号：{} >>> 接收消息：{}",port,message.getPayload());
    }
}
