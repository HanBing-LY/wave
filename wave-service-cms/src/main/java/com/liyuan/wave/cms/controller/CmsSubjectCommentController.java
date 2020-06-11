package com.liyuan.wave.cms.controller;


import com.liyuan.wave.cms.service.CmsSubjectCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:46
 */
@RestController
@RequestMapping("wave/cmssubjectcomment")
public class CmsSubjectCommentController {
    @Autowired
    private CmsSubjectCommentService cmsSubjectCommentService;
}
