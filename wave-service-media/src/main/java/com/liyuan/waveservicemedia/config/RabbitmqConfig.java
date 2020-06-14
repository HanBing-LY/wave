package com.liyuan.waveservicemedia.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

	//消费者并发数量
	public static final int DEFAULT_CONCURRENT = 10;

	@Value("${wlzx.mq.exchange}")
	private String exchange_name;
	@Value("${wlzx.mq.queue}")
	private String queue_name;
	@Value("${wlzx.mq.routingKey}")
	private String routing_key;

	@Bean
	public Exchange getExchange(){
		return  ExchangeBuilder.directExchange(exchange_name).durable(true).build();
	}

	@Bean
	public Queue getQueue(){
		return new Queue(queue_name,true);
	}

	@Bean
	public Binding getBinding(@Autowired Queue queue, @Autowired Exchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with(routing_key).noargs();
	}

	@Bean("customContainerFactory")
	public SimpleRabbitListenerContainerFactory containerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConcurrentConsumers(DEFAULT_CONCURRENT);
		factory.setMaxConcurrentConsumers(DEFAULT_CONCURRENT);
		configurer.configure(factory, connectionFactory);
		return factory;
	}
}