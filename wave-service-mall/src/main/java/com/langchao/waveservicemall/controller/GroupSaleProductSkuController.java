package com.langchao.waveservicemall.controller;

import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavecommon.web.BaseController;
import com.langchao.waveservicemall.pojo.vo.GroupSaleProductSkuVo;
import com.langchao.waveservicemall.service.GroupSaleProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liyuan
 * @create 2020-03-27-10:16-周五
 */
@RestController
@RequestMapping("/group/sale/product/sku")
public class GroupSaleProductSkuController  extends BaseController {

    @Autowired
    private GroupSaleProductSkuService groupSaleProductSkuService;

    /**
     * @Author liyuan
     * @Description 选择商品
     * @return
     */
    @PostMapping
    public JsonResult chooseProduct(@RequestBody GroupSaleProductSkuVo groupSaleProductSkuVo){
        groupSaleProductSkuService.chooseProduct(groupSaleProductSkuVo);
        return success();
    }
}
