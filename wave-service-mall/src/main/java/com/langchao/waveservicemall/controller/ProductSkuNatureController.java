package com.langchao.waveservicemall.controller;

import com.chemguan.business.results.JsonResult;
import com.chemguan.business.results.JsonResultGenerator;
import com.chemguan.entity.ProductSkuNature;
import com.chemguan.service.ProductSkuNatureService;
import com.langchao.wavecommon.web.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product/sku/nature")
public class ProductSkuNatureController  extends BaseController {
    @Autowired
    private ProductSkuNatureService productSkuNatureService;
    /**
     *@Description 后台添加
     *@Author Renjinliang
     *@date 2020/3/23 11:21
     */
    @PostMapping
    public JsonResult add(@RequestBody ProductSkuNature productSkuNature){
        productSkuNatureService.save(productSkuNature);
        return success();
    }

    /**
     * 后台批量删除
     * @param checkIds
     * @return
     */
    @GetMapping("delpl")
    public JsonResult delpl(@RequestParam("checkIds") String checkIds) {
        if(StringUtils.isEmpty(checkIds)){
            return JsonResultGenerator.genFailJsonResult("数据为空!");
        }
        try{
            productSkuNatureService.deleteByIds(checkIds);
        }catch (Exception e){
            return JsonResultGenerator.genFailJsonResult("所选数据已被关联无法删除");
        }
        return success();
    }

    /**
     *@Description 后台修改
     *@Author Renjinliang
     *@date 2020/3/23 11:22
     */
    @PutMapping("/update")
    public JsonResult update(@RequestBody ProductSkuNature productSkuNature){
        productSkuNatureService.update(productSkuNature);
        return success();
    }

    /**
     *@Description 后台详情
     *@Author Renjinliang
     *@date 2020/3/23 11:22
     */
    @GetMapping("/{id}")
    public JsonResult detail(@PathVariable Integer id) {
        return success(productSkuNatureService.findById(id));
    }

    /**
     *@Description 后台list
     *@Author Renjinliang
     *@date 2020/3/24 9:10
     */
    @GetMapping
    public JsonResult list(
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "size",defaultValue = "10") Integer size,
            @RequestParam(name = "id",defaultValue = "") Integer id){
        return success(productSkuNatureService.findManagerList(page, size, id));
    }


}
