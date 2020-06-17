package com.liyuan.wave.webpage.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.liyuan.wave.po.webpage.cms.CmsPage;
import com.liyuan.wave.po.webpage.cms.CmsSite;
import com.liyuan.wave.po.webpage.cms.CmsTemplate;
import com.liyuan.wave.po.webpage.cms.request.QueryPageRequest;
import com.liyuan.wave.po.webpage.cms.response.CmsCode;
import com.liyuan.wave.webpage.mapper.CmsPageMapper;
import com.liyuan.wave.webpage.mapper.CmsSiteMapper;
import com.liyuan.wavecommon.exception.ExceptionCast;
import com.liyuan.wavecommon.util.StringUtils;
import com.liyuan.wavecommon.vo.response.PageInfo;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
@Slf4j
public class CmsPageService extends ServiceImpl<CmsPageMapper, CmsPage> {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CmsTemplateService cmsTemplateService;
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Resource
    private CmsSiteMapper cmsSiteMapper;

    @Value("${langchao.mq.exchange}")
    private String exchangeName;
    @Value("${langchao.mq.routingKey}")
    private String routingKey;


    public PageInfo findList(int pageNo, int pageSize, QueryPageRequest queryPageRequest) {
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        if (StringUtils.isNull(queryPageRequest)) {
            queryPageRequest = new QueryPageRequest();
        }

        IPage<CmsPage> page = new Page<>(pageNo, pageSize);
        QueryWrapper<CmsPage> wrapper = new QueryWrapper<>();
        //根据站点Id、模板Id、页面别名查询页面信息
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
            wrapper.eq("site_id", queryPageRequest.getSiteId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId())) {
            wrapper.eq("template_id", queryPageRequest.getTemplateId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            wrapper.eq("page_aliase", queryPageRequest.getPageAliase());
        }
        //根据创建时间倒序
        wrapper.orderByDesc("page_create_time");
        page = this.page(page, wrapper);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(page.getRecords());
        pageInfo.setTotal(page.getTotal());
        return pageInfo;

    }

    public CmsPage add(CmsPage cmsPage) {
        if (StringUtils.isNull(cmsPage)) {
            cmsPage = new CmsPage();
        }

        //添加业务前，先根据页面名称、站点Id、页面webpath查询CmsPage，此方法用于校验页面是否存在
        QueryWrapper<CmsPage> wrapper = new QueryWrapper<>();
        wrapper.eq("page_name", cmsPage.getPageName());
        wrapper.eq("site_id", cmsPage.getSiteId());
        wrapper.eq("page_web_path", cmsPage.getPageWebPath());
        CmsPage one = this.getOne(wrapper);
        if (StringUtils.isNull(one)) {
            cmsPage.setPageId(null);
            //增加保存
            this.save(cmsPage);
            return cmsPage;
        } else {
            return one;
        }


    }

    /**
     * 根据页面id获取当前页面的静态html
     *
     * @param pageId
     * @return
     */
    public String getPageHtml(String pageId) {

        //获取模型数据
        Map model = getModelByPageId(pageId);

        //获取模板内容
        String template = getTemplateByPageId(pageId);

        //生成页面
        String html = generateHtml(model, template);


        return html;
    }

    /**
     * 获取模型数据
     *
     * @param pageId
     * @return
     */
    private Map getModelByPageId(String pageId) {
        CmsPage one = this.getById(pageId);
        if (StringUtils.isNull(one)) {
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        }
        String dataUrl = one.getDataUrl();
        if (StringUtils.isEmpty(dataUrl)) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAISNULL);
        }
        Map map = restTemplate.getForObject(dataUrl, Map.class);
        return map;


    }

    /**
     * 获取模板内容 CmsPage-->templateId-->CmsTemplate-->templateContent
     *
     * @param pageId
     * @return
     */
    private String getTemplateByPageId(String pageId) {
        CmsPage cmsPage = this.getById(pageId);
        if (StringUtils.isNull(cmsPage)) {
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        }
        String templateId = cmsPage.getTemplateId();
        if (StringUtils.isNull(templateId)) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        CmsTemplate cmsTemplate = cmsTemplateService.getById(templateId);
        if (StringUtils.isNull(cmsTemplate)) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }

        String templateContent = cmsTemplate.getTemplateContent();
        String group = templateContent.substring(0, templateContent.indexOf("/"));
        String url = templateContent.substring(templateContent.indexOf("/") + 1);
        byte[] bytes = fastFileStorageClient.downloadFile(group, url, new DownloadByteArray());
        return new String(bytes);


    }


    /**
     * 静态化成html页容
     *
     * @param model
     * @param templateContent
     * @return
     */
    private String generateHtml(Map model, String templateContent) {
        //创建配置对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //创建模板加载器
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("template", templateContent);
        configuration.setTemplateLoader(stringTemplateLoader);
        //向configuration配置模板加载器
        try {
            Template template = configuration.getTemplate("template");

            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            return html;
        } catch (Exception e) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_SAVEHTMLERROR);
            return null;
        }

    }

    /**
     * 页面的发布
     *
     * @param pageId
     */
    public void post(String pageId) {
        try {
            //页面静态化后保存到fastdfs
            saveHtml(pageId);

            //将pageId发送给rabbitMQ
            sendPostPage(pageId);
        } catch (Exception e) {
            log.error(e.getMessage());
            ExceptionCast.cast(CmsCode.CMS_POSTAGE_FIAL);
        }
    }

    /**
     * 将pageId发送给rabbitMQ
     *
     * @param pageId
     */
    private void sendPostPage(String pageId) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, pageId);
    }

    /**
     * 页面静态化后保存到fastdfs
     *
     * @param pageId
     */
    private void saveHtml(String pageId) throws IOException {
        String htmlContext = getPageHtml(pageId);
        if (StringUtils.isEmpty(htmlContext) || StringUtils.isEmpty(pageId)) {
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        }
        //上传内容至fastdfs
        InputStream is = new ByteArrayInputStream(htmlContext.getBytes());
        StorePath storePath = fastFileStorageClient.uploadFile(is, is.available(), "html", null);
        //将html文件path更新到cmsPage中
        CmsPage cmsPage = this.getById(pageId);
        cmsPage.setHtmlFilePath(storePath.getFullPath());
        this.updateById(cmsPage);


    }

    /**
     * 保存html文件到本地
     *
     * @param pageId
     */
    public void savePageToServerPath(String pageId) {

        //先从fastdfs 下载内容
        CmsPage cmsPage = this.getById(pageId);
        String htmlFilePath = cmsPage.getHtmlFilePath();
        String group = htmlFilePath.substring(0, htmlFilePath.indexOf("/"));
        String url = htmlFilePath.substring(htmlFilePath.indexOf("/") + 1);
        byte[] bytes = fastFileStorageClient.downloadFile(group, url, new DownloadByteArray());
        //读取流
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);


        String sitePhysicalPath = cmsSiteMapper.selectById(cmsPage.getSiteId()).getSitePhysicalPath();
        String pagePhysicalPath = cmsPage.getPagePhysicalPath();
        String pageName = cmsPage.getPageName();
        //保存路径的来源
        String saveUrl = sitePhysicalPath + pagePhysicalPath + "/" + pageName;

        try {
            //本地流，写入流
            FileOutputStream fos = new FileOutputStream(saveUrl);
            IOUtils.copy(bis, fos);
        } catch (Exception e) {
            log.error(e.getMessage());
            ExceptionCast.cast(CmsCode.CMS_POSTAGE_FIAL);
        }
    }

    public String postPageQuick(CmsPage cmsPage) {
        //得到页面信息
        CmsPage one = this.add(cmsPage);
        //页面发布(fastdfs，下载本地)
        this.post(one.getPageId());

        String siteId = cmsPage.getSiteId();
        CmsSite cmsSite = cmsSiteMapper.selectById(siteId);


        //cmsSite.siteDomain+cmsSite.siteWebPath+ cmsPage.pageWebPath + cmsPage.pageName
        String pageUrl = cmsSite.getSiteDomain() + cmsSite.getSiteWebPath() + cmsPage.getPageWebPath() + cmsPage.getPageName();
        return pageUrl;
    }
}
