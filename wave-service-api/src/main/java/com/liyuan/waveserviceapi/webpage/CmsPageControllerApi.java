package com.liyuan.waveserviceapi.webpage;


import com.liyuan.wave.po.webpage.cms.CmsPage;
import com.liyuan.wave.po.webpage.cms.request.QueryPageRequest;
import com.liyuan.wavecommon.vo.response.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="CmsPage管理接口",tags = "CmsPage管理接口")
public interface CmsPageControllerApi {
    //页面查询
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    JsonResult findList(int page, int size, QueryPageRequest queryPageRequest);
    //新增页面
    @ApiOperation("新增页面")
    JsonResult add(CmsPage cmsPage);

    //根据页面id查询页面信息
    @ApiOperation("根据页面id查询页面信息")
    JsonResult findById(String id);

    //修改页面
    @ApiOperation("修改页面")
    JsonResult edit(CmsPage cmsPage);

    //删除页面
    @ApiOperation("删除页面")
    JsonResult delete(String id);

    //页面发布
    @ApiOperation("发布页面")
    JsonResult post(String pageId);


    @ApiOperation("一键发布页面")
    JsonResult postPageQuick(CmsPage cmsPage);
}