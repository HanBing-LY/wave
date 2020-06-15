package com.liyuan.wave.webpage.controller;


import com.liyuan.wave.webpage.service.CmsPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class CmsPagePreviewController {

    @Autowired
    private CmsPageService cmsPageService;

    @GetMapping(value="/cms/preview/{pageId}")
    public void preview(@PathVariable String pageId, HttpServletResponse response){
        //cmspage中的模板(templateId=>cms_template=>template_content)+数据(dataurl)
        String html=cmsPageService.getPageHtml(pageId);

        response.addHeader("content-type","text/html;charset=UTF-8");
        try {
            response.getOutputStream().write(html.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

}
