package com.langchao.waveservicemall.controller;


import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavecommon.web.BaseController;
import com.langchao.waveservicemall.pojo.ColumnNature;
import com.langchao.waveservicemall.pojo.dto.ColumnNatureDTO;
import com.langchao.waveservicemall.service.ColumnNatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("column/nature")
public class ColumnNatureController extends BaseController {
    @Autowired
    private ColumnNatureService columnNatureService;

    /**
    *@Description 后台根据产品分类id查询数据
    *@Author Renjinliang
    *@date 2020/3/24 19:54
    */
    @GetMapping("findByColumnId")
    public JsonResult findByColumnId(@RequestParam(name = "productColumId", defaultValue = "") Integer productColumId,
                                     @RequestParam(name = "columnNatureId", defaultValue = "") String columnNatureId,
                                     @RequestParam(name = "natureValueId", defaultValue = "") String natureValueId){
        List<ColumnNatureDTO> natureDTOList = columnNatureService.findByColumnId(productColumId, columnNatureId, natureValueId);
        return success(natureDTOList);
    }


    @PostMapping
    public JsonResult add(@RequestBody ColumnNature columnNature) {
        columnNatureService.save(columnNature);
        return success();
    }

    @GetMapping
    public JsonResult list(@RequestParam(name = "id", defaultValue = "")Integer id,
                       @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return success(columnNatureService.findManagerList(id, page, size));
    }

}
