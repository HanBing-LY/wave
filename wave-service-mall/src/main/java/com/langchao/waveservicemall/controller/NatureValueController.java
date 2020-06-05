package com.langchao.waveservicemall.controller;

import com.chemguan.business.results.JsonResult;
import com.chemguan.business.results.JsonResultGenerator;
import com.chemguan.entity.NatureValue;
import com.chemguan.service.NatureValueService;
import com.chemguan.service.ProductInfoService;
import com.langchao.wavecommon.web.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("nature/value")
public class NatureValueController  extends BaseController {
    @Autowired
    private NatureValueService natureValueService;
    @Autowired
    private ProductInfoService productInfoService;

    /**
    *@Description 后台根据id查询数据
    *@Author Renjinliang
    *@date 2020/3/24 15:26
    */
    @GetMapping("findByNatureId")
    public List<NatureValue> findByNatureId(@RequestParam(name = "id", defaultValue = "") Integer id){
        List<NatureValue> natureValueList = natureValueService.findByNatureId(id);
        return natureValueList;
    }


    /**
    *@Description 后台添加
    *@Author Renjinliang
    *@date 2020/3/23 9:17
    */
    @PostMapping
    public JsonResult add(@RequestBody NatureValue natureValue){
        natureValueService.save(natureValue);
        return success();
    }

    /**
     * @Description 后台删除
     * @Author Renjinliang
     * @date 2020/3/20 15:58
     */
    @GetMapping("delpl")
    public JsonResult delpl(@RequestParam("checkIds") String checkIds) {
        if (StringUtils.isEmpty(checkIds)) {
            return JsonResultGenerator.genFailJsonResult("数据为空!");
        }
        try {
            natureValueService.deleteByIds(checkIds);
        } catch (Exception e) {
            return JsonResultGenerator.genFailJsonResult("所选数据已被关联无法删除");
        }
        return success();
    }

    /**
    *@Description 后台修改
    *@Author Renjinliang
    *@date 2020/3/23 9:20
    */
    @PutMapping("/update")
    public JsonResult update(@RequestBody NatureValue natureValue){
        natureValueService.update(natureValue);
        return success();
    }

    /**
     * @Description 后台根据id查询详情
     * @Author Renjinliang
     * @date 2020/3/20 16:01
     */
    @GetMapping("/{id}")
    public JsonResult detail(@PathVariable Integer id) {
        return success(natureValueService.findById(id));
    }

    /**
    *@Description 后台list
    *@Author Renjinliang
    *@date 2020/3/23 9:30
    *@param id 属性id
    */
    @GetMapping
    public JsonResult list(@RequestParam(name = "id", defaultValue = "")Integer id,
                       @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @RequestParam(name = "size", defaultValue = "10") Integer size){
        return success(natureValueService.findManagerList(id, page, size));
    }
}
