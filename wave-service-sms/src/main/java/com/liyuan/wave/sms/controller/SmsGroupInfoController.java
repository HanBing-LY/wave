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

    @Autowired
    private SmsGroupInfoService smsGroupInfoService;

    /**
     * @description  根据拼团商品查所有拼团
     * @param articleNumber
     * @return
     */
    @GetMapping("/listGroupClubByPage")
    public JsonResult listGroupClubByPage(@RequestParam(name = "articleNumber",required = true) String articleNumber){
        return success(smsGroupInfoService.listGroupClubByPage(articleNumber,false));
    }

    /**
     * @description  根据groupNumber拼团编码查询拼购信息
     * @param groupNumber
     * @return
     */
    @GetMapping("/groupClubByGroupNumber")
    public JsonResult groupClubByGroupNumber(@RequestParam(name = "groupNumber",required = true) String groupNumber){
        return success(smsGroupInfoService.groupClubByGroupNumber(groupNumber));
    }

    /**
     * @description  拼团加入,生成订单
     * @param groupNumber
     * @return
     */
    @GetMapping("/group/go")
    public JsonResult joinGroup(@RequestParam(name = "groupNumber",required = true) String groupNumber){
        smsGroupInfoService.joinGroup(groupNumber);
        return success();
    }

    /**
     * @description  建团,生成订单
     * @param articleNumber
     * @return
     */
    @GetMapping("/group/create")
    public JsonResult createGroup(@RequestParam(name = "articleNumber",required = true) String articleNumber,@RequestParam(name = "groupSaleProductId",required = true) Long groupSaleProductId){
        smsGroupInfoService.createGroup(articleNumber,groupSaleProductId);
        return success();
    }
}
