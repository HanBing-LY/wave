package com.langchao.waveservicemall.controller;

import com.chemguan.business.results.JsonResult;
import com.chemguan.entity.GroupSale;
import com.chemguan.service.GroupInfoDetailService;
import com.chemguan.service.GroupSaleService;
import com.langchao.wavecommon.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author liyuan
 * @create 2020-03-20-11:22-周五
 */
@RestController
@RequestMapping("/group")
public class GroupSaleController  extends BaseController {

    /**
     * 组件注入
     */
    @Autowired
    private GroupInfoDetailService groupInfoDetailService;
    @Autowired
    private GroupSaleService groupSaleService;

    /**
     * @Author liyuan
     * @Description 根据产品id 分页查询拼购俱乐部
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/listGroupClubByPage")
    public JsonResult listGroupClubByPage(@RequestParam(name = "productId",required = true) Integer productId, @RequestParam(name = "page",required = false) Integer pageNum, @RequestParam(name = "size",required = false) Integer pageSize){
        return success(groupInfoDetailService.listGroupClubByPage(productId,pageNum,pageSize));
    }

    /**
     * @Author liyuan
     * @Description 1-0-1-1-5 俱乐部拼购-商品详情-加入拼购 根据groupNumber拼团编码查询拼购信息
     * @param groupNumber
     * @return
     */
    @GetMapping("/groupClubByGroupNumber")
    public JsonResult groupClubByGroupNumber(@RequestParam(name = "groupNumber",required = true) Integer groupNumber){
        return success(groupInfoDetailService.groupClubByGroupNumber(groupNumber));
    }

    /**
     * @Author liyuan
     * @Description     1-0-1-5-1 联盟拼购-发起拼购成功-确认订单-支付成功-分享
     * @param groupNumber
     * @return
     */
    @GetMapping("/groupShare")
    public JsonResult groupShare(@RequestParam(name = "groupNumber",required = true) Integer groupNumber,@RequestParam(name = "userId",required = true) Integer userId){
        return success(groupInfoDetailService.groupShare(groupNumber,userId));
    }

    /**
     * @Author liyuan
     * @Description     1-0-1-5-0 联盟拼购-我的拼团
     * @param userId
     * @return
     */
    @GetMapping("/my/all")
    public JsonResult myAll(@RequestParam(name = "userId",required = true) Integer userId){
        return success(groupInfoDetailService.myAll(userId));
    }

    /**
     * @Author liyuan
     * @Description 添加拼团控制
     * @param groupSale
     */
    @PostMapping
    public JsonResult add(@RequestBody GroupSale groupSale){
        groupSaleService.addGroupSale(groupSale);
        return success();
    }

    /**
     * @Author liyuan
     * @Description 修改拼团控制
     * @param groupSale
     */
    @PutMapping
    public JsonResult update(@RequestBody GroupSale groupSale){
        groupSaleService.updateGroupSale(groupSale);
        return success();
    }

    /**
     * @Author liyuan
     * @Description 分页查询秒杀控制
     */
    @GetMapping
    public JsonResult list(@RequestParam(name = "flag",required = false)Integer flag, @RequestParam(name = "startTime",required = false) Date startTime, @RequestParam(name = "endTime",required = false) Date endTime,
                       @RequestParam(name = "page",required = false)Integer page, @RequestParam(name = "size",required = false)Integer size){
        return success(groupSaleService.list(flag,startTime,endTime,page,size));
    }

    /**
     * @author liyuan
     * @Description 查询秒杀控制
     * @param id
     * @return
     */
    @GetMapping("/sale/selectOne")
    public JsonResult selectOne(@RequestParam(name = "id",required = true ) Integer id){
        return success(groupSaleService.selectOne(id));
    }

    /**
     * @author liyuan
     * @Description 删除秒杀
     * @param id
     * @return
     */
    @GetMapping("/sale/delpl")
    public JsonResult delpl(@RequestParam(name = "checkIds",required = true ) String id){
        groupSaleService.delpl(id);
        return success();
    }
}
