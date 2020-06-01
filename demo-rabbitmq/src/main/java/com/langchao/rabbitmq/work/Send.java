package com.langchao.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaoyuan.allrabbitmq.RabbitMqConnectionUtil;

public class Send {

    private final static String QUEUE_NAME = "test_queue_work";

    /**
     * 轮询分发
     1、消费者1和消费者2获取到的消息内容是不同的，同一个消息只能被一个消费者获取。
     2、消费者1和消费者2获取到的消息的数量是相同的，一个是消费奇数号消息，一个是偶数。
     * 公平分发(能者多劳模式)
     *
     * @param argv
     * @throws Exception
     */
    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = RabbitMqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for (int i = 0; i < 100; i++) {
            // 消息内容
            String message = "" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
//            Thread.sleep(i * 10);
        }
        channel.close();
        connection.close();
    }
}
