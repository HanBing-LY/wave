package com.liyuan.rabbitmq.simple;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liyuan
 * @description
 * @date 2020-06-14-20:51
 */
@SpringBootTest
public class HelloSenderTest {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Test
    public void send() {
        int i = 10;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        String context = "hello " + date;
        System.out.println("Sender : " + i +" "+ context);
        //简单对列的情况下routingKey即为Q名
        this.rabbitTemplate.convertAndSend("q_hello", context);
    }

}