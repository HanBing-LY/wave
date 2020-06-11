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

import com.liyuan.wave.po.entity.cms.CmsSubjectProductRelationEntity;
import com.liyuan.wave.ums.service.CmsSubjectProductRelationService;
import com.liyuan.common.utils.PageUtils;
import com.liyuan.common.utils.R;



/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:45
 */
@RestController
@RequestMapping("wave/cmssubjectproductrelation")
public class CmsSubjectProductRelationController {
    @Autowired
    private CmsSubjectProductRelationService cmsSubjectProductRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wave:cmssubjectproductrelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cmsSubjectProductRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wave:cmssubjectproductrelation:info")
    public R info(@PathVariable("id") Long id){
		CmsSubjectProductRelationEntity cmsSubjectProductRelation = cmsSubjectProductRelationService.getById(id);

        return R.ok().put("cmsSubjectProductRelation", cmsSubjectProductRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wave:cmssubjectproductrelation:save")
    public R save(@RequestBody CmsSubjectProductRelationEntity cmsSubjectProductRelation){
		cmsSubjectProductRelationService.save(cmsSubjectProductRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wave:cmssubjectproductrelation:update")
    public R update(@RequestBody CmsSubjectProductRelationEntity cmsSubjectProductRelation){
		cmsSubjectProductRelationService.updateById(cmsSubjectProductRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wave:cmssubjectproductrelation:delete")
    public R delete(@RequestBody Long[] ids){
		cmsSubjectProductRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
