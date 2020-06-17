package com.liyuan.wave.pms.controller;

import com.liyuan.wave.pms.po.vo.PmsProductColumnSaveVo;
import com.liyuan.wave.pms.service.PmsProductColumnService;
import com.liyuan.wavecommon.vo.response.JsonResult;
import com.liyuan.wavecommon.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @description pms_product_column
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@RestController
@RequestMapping("pms/product/column")
public class PmsProductColumnController extends BaseController {

    @Autowired
    private PmsProductColumnService pmsProductColumnService;

    /**
     * @description 新增商品分类
     * @param productColumnSaveVo
     * @return
     */
    @PostMapping("/save")
    public JsonResult add(@RequestBody PmsProductColumnSaveVo productColumnSaveVo){
        pmsProductColumnService.addProductColumn(productColumnSaveVo);
        return success();
    }

    /**
     * @description 保存商品分类名称和图片url
     * @param pmsProductColumnSaveVo
     * @return
     */
    @PutMapping("/update")
    public JsonResult updateProductColumn(@RequestBody PmsProductColumnSaveVo pmsProductColumnSaveVo){
        pmsProductColumnService.updateProductColumn(pmsProductColumnSaveVo);
        return success();
    }

    /**
     * @description 禁用商品分类
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    public JsonResult deleteProductColumn(@RequestParam(name = "ids") String ids){
        pmsProductColumnService.disabled(ids);
        return success();
    }

    /**
     * @description 查看某一级的所有分类
     * @param id
     * @return
     */
    @GetMapping("/goods/all")
    public JsonResult getNextChildrenByParentId(@RequestParam(name = "id") Long id){
        return success(pmsProductColumnService.getNextChildrenByParentId(id));
    }
}
