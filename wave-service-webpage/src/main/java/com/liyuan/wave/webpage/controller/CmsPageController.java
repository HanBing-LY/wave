package com.liyuan.wave.webpage.controller;


import com.liyuan.wave.po.webpage.cms.CmsPage;
import com.liyuan.wave.po.webpage.cms.request.QueryPageRequest;
import com.liyuan.wave.webpage.service.CmsPageService;
import com.liyuan.wavecommon.vo.response.JsonResult;
import com.liyuan.wavecommon.web.BaseController;
import com.liyuan.waveserviceapi.webpage.CmsPageControllerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/page")
public class CmsPageController extends BaseController implements CmsPageControllerApi {

	@Autowired
  	private CmsPageService cmsPageService;

    /**
     * 分页查询
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
   	@Override
    @GetMapping("/list/{page}/{size}")
    public JsonResult findList(@PathVariable int page, @PathVariable
            int size, QueryPageRequest queryPageRequest) {
        return success(cmsPageService.findList(page, size, queryPageRequest));
    }

    /**
     * 新增,返回增加页面的id
     * @param cmsPage
     * @return
     */
    @Override
    @PostMapping("/add")
    public JsonResult add(@RequestBody CmsPage cmsPage) {
        CmsPage one = cmsPageService.add(cmsPage);
        return success("增加成功",one.getPageId());
    }



    /**
     * 修改时回显数据
     * @param id
     * @return
     */
    @Override
    @GetMapping("/get/{id}")
    public JsonResult findById(@PathVariable String id) {
        return success(cmsPageService.getById(id));
    }

    /**
     * 修改功能
     * @param cmsPage
     * @return
     */
    @Override
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody CmsPage cmsPage) {
        cmsPageService.updateById(cmsPage);
        return success();
    }

    /**
     * 删除功能
     * @param id
     * @return
     */
    @Override
    @DeleteMapping("/del/{id}")
    public JsonResult delete(@PathVariable  String id) {
         cmsPageService.removeById(id);
         return  success();
    }

    /**
     * 发布功能
     * @param pageId
     * @return
     */
    @Override
    @PostMapping("/postPage/{pageId}")
    public JsonResult post(@PathVariable String pageId) {
        cmsPageService.post(pageId);
        return success();
    }


    /**
     * 一键发布课程
     * @param cmsPage
     * @return
     */
    @Override
    @PostMapping("/postPageQuick")
    public JsonResult postPageQuick(@RequestBody CmsPage cmsPage) {
        return success("发布课程成功",cmsPageService.postPageQuick(cmsPage));
    }
}