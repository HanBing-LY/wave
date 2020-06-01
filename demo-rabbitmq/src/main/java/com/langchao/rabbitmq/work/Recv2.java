package com.langchao.rabbitmq.work;


import com.langchao.rabbitmq.util.RabbitMqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;


public class Recv2 {

    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] argv) throws Exception {

        // 获取到连接以及mq通道
        Connection connection = RabbitMqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        // 定义队列的消费者
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            //开启这行 表示使用手动确认模式
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received2 '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> { });

    }
}
