package com.liyuan.wave.webpage.listener;


import com.liyuan.wave.po.cms.CmsPage;
import com.liyuan.wave.webpage.service.CmsPageService;
import com.liyuan.wave.common.util.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostPageListener {
    @Autowired
    private CmsPageService cmsPageService;
    @RabbitListener(queues = "${langchao.mq.queue}")
    public void receive(String pageId){
        //从消息队列中获取pageId
        //根据pageId获取到CmsPage对象
        CmsPage cmsPage = cmsPageService.getById(pageId);
        if(StringUtils.isNotNull(cmsPage)) {
            //从fastdfs中下载起来，保存在本地
            cmsPageService.savePageToServerPath(pageId);
        }
    }

}
