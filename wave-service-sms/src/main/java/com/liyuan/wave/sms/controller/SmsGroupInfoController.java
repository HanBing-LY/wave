package com.liyuan.wave.sms.controller;

import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.wave.sms.service.SmsGroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description sms_group_info
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@RestController
@RequestMapping
public class SmsGroupInfoController extends BaseController {

    /**
     * 组件注入
     */
    @Autowired
    private SmsGroupInfoService smsGroupInfoService;

    /**
     * @description  根据拼团商品查所有拼团
     * @param productSkuId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/listGroupClubByPage")
    public JsonResult listGroupClubByPage(@RequestParam(name = "productSkuId",required = true) Long productSkuId, @RequestParam(name = "page",required = false) Integer pageNum, @RequestParam(name = "size",required = false) Integer pageSize){
        return success(smsGroupInfoService.listGroupClubByPage(productSkuId,pageNum,pageSize));
    }

    /**
     * @description  根据groupNumber拼团编码查询拼购信息
     * @param groupNumber
     * @return
     */
    @GetMapping("/groupClubByGroupNumber")
    public JsonResult groupClubByGroupNumber(@RequestParam(name = "groupNumber",required = true) Integer groupNumber){
        return success(smsGroupInfoService.groupClubByGroupNumber(groupNumber));
    }

}
