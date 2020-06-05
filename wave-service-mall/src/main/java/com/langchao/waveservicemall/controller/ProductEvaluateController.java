package com.langchao.waveservicemall.controller;

import com.chemguan.business.results.JsonResult;
import com.chemguan.business.results.JsonResultGenerator;
import com.chemguan.entity.ProductEvaluate;
import com.chemguan.service.ProductEvaluateService;
import com.langchao.wavecommon.web.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liyuan
 * @create 2020-03-20-10:46-周五
 */
@RestController
@RequestMapping("/product/evaluate")
public class ProductEvaluateController  extends BaseController {

    /**
     * 组件注入
     */
    @Autowired
    private ProductEvaluateService productEvaluateService;

    /**
     * @Author liyuan
     * @Description 根据产品id 分页查询评论
     * @param productId 产品Id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/listProductEvaluateByPage")
    public JsonResult listProductEvaluateByPage(@RequestParam(name = "productId",required = true) Integer productId,@RequestParam(name = "page",required = false) Integer pageNum,@RequestParam(name = "size",required = false) Integer pageSize){
        return success(productEvaluateService.listProductEvaluateByPage(productId,pageNum,pageSize));
    }


    /**
     *@Description 后台添加
     *@Author Renjinliang
     *@date 2020/3/23 11:21
     */
    @PostMapping
    public JsonResult add(@RequestBody ProductEvaluate productEvaluate){
        productEvaluateService.save(productEvaluate);
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
            productEvaluateService.deleteByIds(checkIds);
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
    public JsonResult update(@RequestBody ProductEvaluate productEvaluate){
        productEvaluateService.update(productEvaluate);
        return success();
    }
    /**
     *@Description 后台详情
     *@Author Renjinliang
     *@date 2020/3/23 11:22
     */
    @GetMapping("/{id}")
    public JsonResult detail(@PathVariable Integer id) {
        return success(productEvaluateService.findById(id));
    }

    /**
     *@Description 后台list
     *@Author Renjinliang
     *@date 2020/3/23 11:22
     */
    @GetMapping
    public JsonResult list(
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "size",defaultValue = "10") Integer size,
            @RequestParam(name = "id",defaultValue = "") Integer id){
        return success(productEvaluateService.findManagerList(page, size,id));
    }
}
