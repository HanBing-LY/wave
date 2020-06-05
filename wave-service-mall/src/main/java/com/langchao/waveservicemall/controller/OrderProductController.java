package com.langchao.waveservicemall.controller;

import com.chemguan.business.results.JsonResult;
import com.chemguan.business.results.JsonResultGenerator;
import com.chemguan.entity.OrderProduct;
import com.chemguan.service.OrderProductService;
import com.langchao.wavecommon.web.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order/product")
public class OrderProductController  extends BaseController {
    @Autowired
    private OrderProductService orderProductService;

    /**
     * @Description 后台添加
     * @Author Renjinliang
     * @date 2020/3/23 11:21
     */
    @PostMapping
    public JsonResult add(@RequestBody OrderProduct orderProduct) {
        orderProductService.save(orderProduct);
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
            orderProductService.deleteByIds(checkIds);
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
    public JsonResult update(@RequestBody OrderProduct orderProduct) {
        orderProductService.update(orderProduct);
        return success();
    }

    /**
     * @Description 后台详情
     * @Author Renjinliang
     * @date 2020/3/23 11:22
     */
    @GetMapping("/{id}")
    public JsonResult detail(@PathVariable Integer id) {
        return success(orderProductService.findById(id));
    }

    @GetMapping
    public JsonResult list(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "id", defaultValue = "") Integer id) {
        return success(orderProductService.findManagerList(page, size, id));
    }
}
