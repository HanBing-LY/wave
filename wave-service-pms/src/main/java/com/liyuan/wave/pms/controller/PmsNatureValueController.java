package com.liyuan.wave.pms.controller;

import com.liyuan.wave.po.pms.vo.PmsNatureValueSaveVo;
import com.liyuan.wave.pms.service.PmsNatureValueService;
import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @description pms_nature_value
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@RestController
@RequestMapping("pms/nature/value")
public class PmsNatureValueController extends BaseController {

    @Autowired
    private PmsNatureValueService pmsNatureValueService;

    /**
     * @description 新增分类属性值
     * @param pmsNatureValueSaveVo
     * @return
     */
    @PostMapping("/save")
    public JsonResult add(@RequestBody @Valid PmsNatureValueSaveVo pmsNatureValueSaveVo){
        pmsNatureValueService.add(pmsNatureValueSaveVo);
        return success();
    }

    /**
     * @description 修改分类属性值
     * @param pmsNatureValueSaveVo
     * @return
     */
    @PutMapping("/update")
    public JsonResult update(@RequestBody PmsNatureValueSaveVo pmsNatureValueSaveVo){
        pmsNatureValueService.modify(pmsNatureValueSaveVo);
        return success();
    }

    /**
     * @description 禁用属性值
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    public JsonResult deleteProductColumn(@RequestParam(name = "ids") String ids){
        pmsNatureValueService.disable(ids);
        return success();
    }

    /**
     * @description 查询当前分类下的所有有效通用属性
     * @param id
     * @return
     */
    @GetMapping("/nature/current")
    public JsonResult queryByColumnId(@RequestParam(name = "id") Long id,@RequestParam(name = "pageNo") Long pageNo,@RequestParam(name = "pageSize") Long pageSize){
        return success(pmsNatureValueService.queryByColumnNatureId(id,pageNo,pageSize));
    }
}
