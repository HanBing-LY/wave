package com.langchao.waveservicemq.listener;

import com.langchao.waveservicemq.bindings.ReceiveMessage;
import com.langchao.waveserviceuser.service.UserService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@EnableBinding(ReceiveMessage.class)//指信道channel和exchange绑定在一起
public class MqListener {

	@Resource
	private UserService userService;

	@StreamListener(ReceiveMessage.INPUT)//监听队列，用于消费者的队列的消息接收
	public void receive(String pageId){
		//todo pageId校验
		userService.savePageToServerPath(pageId);
	}
}
