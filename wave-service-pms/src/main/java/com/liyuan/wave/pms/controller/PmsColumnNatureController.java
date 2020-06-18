package com.liyuan.wave.pms.controller;

import com.liyuan.wave.pms.po.vo.PmsColumnNatureSaveVo;
import com.liyuan.wave.pms.service.PmsColumnNatureService;
import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @description pms_column_nature
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@RestController
@RequestMapping("pms/column/nature")
public class PmsColumnNatureController extends BaseController {

    @Autowired
    private PmsColumnNatureService pmsColumnNatureService;

    /**
     * @description 新增通用分类属性
     * @param pmsColumnNatureSaveVo
     * @return
     */
    @PostMapping("/save")
    public JsonResult add(@RequestBody @Valid PmsColumnNatureSaveVo pmsColumnNatureSaveVo){
        pmsColumnNatureService.add(pmsColumnNatureSaveVo);
        return success();
    }

    /**
     * @description 修改分类属性名称
     * @param pmsColumnNatureSaveVo
     * @return
     */
    @PutMapping("/update")
    public JsonResult update(@RequestBody PmsColumnNatureSaveVo pmsColumnNatureSaveVo){
        pmsColumnNatureService.modify(pmsColumnNatureSaveVo);
        return success();
    }

    /**
     * @description 禁用通用分类
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    public JsonResult deleteProductColumn(@RequestParam(name = "ids") String ids){
        pmsColumnNatureService.disable(ids);
        return success();
    }

    /**
     * @description 查询当前分类下的所有有效通用属性
     * @param id
     * @return
     */
    @GetMapping("/column/current")
    public JsonResult queryByColumnId(@RequestParam(name = "id") Long id,@RequestParam(name = "pageNo") Long pageNo,@RequestParam(name = "pageSize") Long pageSize){
        return success(pmsColumnNatureService.queryByColumnId(id,pageNo,pageSize));
    }

}
