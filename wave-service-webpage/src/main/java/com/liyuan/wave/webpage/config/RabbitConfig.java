package com.liyuan.wave.webpage.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${langchao.mq.exchange}")
    private String exchange_name;
    @Value("${langchao.mq.queue}")
    private String quque_name;
    @Value("${langchao.mq.routingKey}")
    private String routing_key;

    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.directExchange(exchange_name).durable(true).build();
    }

    @Bean
    public Queue queue(){
        return new Queue(quque_name,true);
    }

    @Bean
    public Binding binding(@Autowired Queue queue, @Autowired Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(routing_key).noargs();
    }
}
