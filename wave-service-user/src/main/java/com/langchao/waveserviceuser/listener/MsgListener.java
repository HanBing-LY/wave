package com.langchao.waveserviceuser.listener;


import com.langchao.wavecommon.util.StringUtils;
import com.langchao.waveserviceuser.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MsgListener {

    @Resource
    private UserService userService;

    @RabbitListener(queues = "${wlzx.mq.queue}")
    public void receive(String id) {
//       从消息队列中获取Id
        if(StringUtils.isNotEmpty(id)){
//            从fastdfs中下载,保存在本地
            userService.savePageToServerPath(id);
        }

    }
}