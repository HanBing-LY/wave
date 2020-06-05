package com.langchao.waveservicemall.controller;


import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavecommon.web.BaseController;
import com.langchao.waveservicemall.pojo.FlashSale;
import com.langchao.waveservicemall.service.FlashSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liyuan
 * @create 2020-03-24-10:21-周二
 */
@RestController
@RequestMapping("/flash/sale")
public class FlashSaleController extends BaseController {

    @Autowired
    private FlashSaleService flashSaleService;

    /**
     * @Author liyuan
     * @Description 添加秒杀控制
     * @param flashSale
     */
    @PostMapping
    public JsonResult add(@RequestBody FlashSale flashSale){
        flashSaleService.addFlashSale(flashSale);
        return success();
    }

    /**
     * @Author liyuan
     * @Description 修改秒杀控制
     * @param flashSale
     */
    @PutMapping
    public JsonResult update(@RequestBody FlashSale flashSale){
        flashSaleService.updateFlashSale(flashSale);
        return success();
    }

    /**
     * @Author liyuan
     * @Description 修改秒杀控制
     * @param flashSale
     */
    @GetMapping("/delp")
    public JsonResult delete(@RequestBody FlashSale flashSale){
        flashSaleService.updateFlashSale(flashSale);
        return success();
    }

    /**
     * @Author liyuan
     * @Description 分页查询秒杀控制
     */
    @GetMapping("/list")
    public JsonResult list(@RequestParam(name = "flag",required = false)Integer flag,@RequestParam(name = "startTime",required = false) Date startTime, @RequestParam(name = "endTime",required = false) Date endTime,
            @RequestParam(name = "page",required = false)Integer page,@RequestParam(name = "size",required = false)Integer size){
        return success(flashSaleService.list(flag,startTime,endTime,page,size));
    }

    /**
     * @author liyuan
     * @Description 查询秒杀控制
     * @param id
     * @return
     */
    @GetMapping("/selectOne")
    public JsonResult selectOne(@RequestParam(name = "id",required = true ) Integer id){
        return success(flashSaleService.selectOne(id));
    }

    /**
     * @author liyuan
     * @Description 删除秒杀
     * @param checkIds
     * @return
     */
    @GetMapping("/delpl")
    public JsonResult delpl(@RequestParam(name = "checkIds",required = true ) String checkIds){
        flashSaleService.delpl(checkIds);
        return success();
    }
}
