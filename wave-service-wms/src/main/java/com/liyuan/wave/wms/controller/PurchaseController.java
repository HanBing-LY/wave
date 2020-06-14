package com.liyuan.wave.wms.controller;


import com.liyuan.wave.wms.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:46
 */
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

}
