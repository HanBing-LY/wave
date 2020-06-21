package com.liyuan.wave.ums.controller;

import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.wave.po.ums.vo.UmsUserInfoScoreVo;
import com.liyuan.wave.ums.service.UmsUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liyuan
 * @description
 * @date 2020-06-15 15:47
 */
@RestController
@RequestMapping("/ums/user")
public class UmsUserInfoController extends BaseController {

    @Autowired
    private UmsUserInfoService umsUserInfoService;

    /**
     * @description 添加用户积分
     * @param umsUserInfoScoreVo
     * @return
     */
    @PutMapping("/score")
    public JsonResult incrementScore(@RequestBody UmsUserInfoScoreVo umsUserInfoScoreVo){
        umsUserInfoService.incrementScore(umsUserInfoScoreVo);
        return success();
    }
}
