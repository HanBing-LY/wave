package com.langchao.waveservicemq.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MediaQueueReceive {

	final String INPUT="队列名字";

	@Input(INPUT)//@Input注解标识输入通道，通过该输入通道接收到的消息进入应用程序
	SubscribableChannel input();
}
