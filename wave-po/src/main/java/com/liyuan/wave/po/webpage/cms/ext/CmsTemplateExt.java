package com.liyuan.wave.po.webpage.cms.ext;


import com.liyuan.wave.po.webpage.cms.CmsTemplate;
import lombok.Data;
import lombok.ToString;



@Data
@ToString
public class CmsTemplateExt extends CmsTemplate {

    //模版内容
    private String templateValue;

}
