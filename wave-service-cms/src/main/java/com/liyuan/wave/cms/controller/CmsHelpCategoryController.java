package com.liyuan.wave.cms.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liyuan.wave.po.entity.cms.CmsHelpCategoryEntity;
import com.liyuan.wave.ums.service.CmsHelpCategoryService;
import com.liyuan.common.utils.PageUtils;
import com.liyuan.common.utils.R;



/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:44
 */
@RestController
@RequestMapping("wave/cmshelpcategory")
public class CmsHelpCategoryController {
    @Autowired
    private CmsHelpCategoryService cmsHelpCategoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wave:cmshelpcategory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cmsHelpCategoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wave:cmshelpcategory:info")
    public R info(@PathVariable("id") Long id){
		CmsHelpCategoryEntity cmsHelpCategory = cmsHelpCategoryService.getById(id);

        return R.ok().put("cmsHelpCategory", cmsHelpCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wave:cmshelpcategory:save")
    public R save(@RequestBody CmsHelpCategoryEntity cmsHelpCategory){
		cmsHelpCategoryService.save(cmsHelpCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wave:cmshelpcategory:update")
    public R update(@RequestBody CmsHelpCategoryEntity cmsHelpCategory){
		cmsHelpCategoryService.updateById(cmsHelpCategory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wave:cmshelpcategory:delete")
    public R delete(@RequestBody Long[] ids){
		cmsHelpCategoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
