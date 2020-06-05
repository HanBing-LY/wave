package com.langchao.waveservicemall.controller;

import com.chemguan.business.results.JsonResult;
import com.chemguan.business.results.JsonResultGenerator;
import com.chemguan.entity.ProductInfo;
import com.chemguan.entity.ProductSku;
import com.chemguan.service.ProductInfoService;
import com.chemguan.service.ProductSkuNatureService;
import com.chemguan.service.ProductSkuService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liyuan
 * @create 2020-03-20-11:46-周五
 */
@RestController
@RequestMapping("/product/sku")
public class ProductSkuController  extends BaseController {

    /**
     * 组件注入
     */
    @Autowired
    private ProductSkuService productSkuService;
    @Autowired
    private ProductSkuNatureService productSkuNatureService;
    @Autowired
    private ProductInfoService productInfoService;

    /**
     * @param productId
     * @return
     * @Author liyuan
     * @Description 根据产品id 列举产品属性信息
     */
    @GetMapping("/selectProductNormByProductId")
    public JsonResult selectProductNormByProductId(@RequestParam(name = "productId", required = true) Integer productId) {
        return success(productSkuNatureService.selectProductNormByProductId(productId));
    }
    /**
     * @Author liyuan
     * @Description 校验产品库存
     * @return
     */
//    @PostMapping("/getStock")
//    public JsonResult getStock(@RequestBody ProductVo productVo){
//        return success(productSkuNatureService.getStock(productVo));
//    }

    /**
     * @param id             产品id
     * @param stock          库存
     * @param columnNatureId 分类属性id
     * @param natureValueId  分类属性值id
     * @Description
     * @Author Renjinliang
     * @date 2020/3/24 20:55
     */
    @PostMapping("addProductSku")
    public JsonResult addProductSku(@RequestParam(name = "id", defaultValue = "") Integer id,
                                @RequestParam(name = "stock", defaultValue = "") Integer stock,
                                @RequestParam(name = "columnNatureId", defaultValue = "") String columnNatureId,
                                @RequestParam(name = "natureValueId", defaultValue = "") String natureValueId) {

        productSkuService.addProductSku(id, stock, columnNatureId, natureValueId);
        return success();
    }


    /**
     * @Description 后台添加
     * @Author Renjinliang
     * @date 2020/3/23 11:21
     */
    @PostMapping
    public JsonResult add(@RequestBody ProductSku productSku) {
        productSkuService.save(productSku);
        return success();
    }

    /**
     * 后台批量删除
     *
     * @param checkIds
     * @return
     */
    @GetMapping("delpl")
    public JsonResult delpl(@RequestParam("checkIds") String checkIds) {
        if (StringUtils.isEmpty(checkIds)) {
            return JsonResultGenerator.genFailJsonResult("数据为空!");
        }
        try {
            productSkuService.deleteByIds(checkIds);
        } catch (Exception e) {
            return JsonResultGenerator.genFailJsonResult("所选数据已被关联无法删除");
        }
        return success();
    }

    /**
     * @Description 后台修改
     * @Author Renjinliang
     * @date 2020/3/23 11:22
     */
    @PutMapping("/update")
    public JsonResult update(@RequestBody ProductSku productSku) {
        productSkuService.update(productSku);
        return success();
    }

    /**
     * @Description 后台详情
     * @Author Renjinliang
     * @date 2020/3/23 11:22
     */
    @GetMapping("/{id}")
    public JsonResult detail(@PathVariable Integer id) {
        return success(productSkuService.findById(id));
    }

    /**
     * @Description 后台list
     * @Author Renjinliang
     * @date 2020/3/24 9:10
     */
    @GetMapping
    public JsonResult list(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "id", defaultValue = "") Integer id) {
        return success(productSkuService.findManagerList(page, size, id));
    }



}
