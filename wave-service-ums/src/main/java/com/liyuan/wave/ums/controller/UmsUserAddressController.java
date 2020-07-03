package com.liyuan.wave.ums.controller;

import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.wave.ums.po.dto.UmsUserAddressSaveVo;
import com.liyuan.wave.ums.service.UmsUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liyuan
 * @description
 * @date 2020-06-15 15:47
 */
@RestController
@RequestMapping("/ums/user/address")
public class UmsUserAddressController extends BaseController {

    @Autowired
    private UmsUserAddressService umsUserAddressService;

    /**
     * @description 查询用户的地址
     * @param userId
     * @return
     */
    @GetMapping("/by/userId")
    public JsonResult queryByUserId(@RequestParam Long userId){
        return success(umsUserAddressService.queryByUserId(userId));
    }

    /**
     * @description 查询用户的地址
     * @param id
     * @return
     */
    @GetMapping
    public JsonResult selectById(@RequestParam Long id){
        return success(umsUserAddressService.selectById(id));
    }

    /**
     * @description 新添地址
     * @param umsUserAddressSaveVo
     * @return
     */
    @PostMapping
    public JsonResult create(@RequestParam UmsUserAddressSaveVo umsUserAddressSaveVo){
        umsUserAddressService.create(umsUserAddressSaveVo);
        return success();
    }

    /**
     * @description 修改地址
     * @param umsUserAddressSaveVo
     * @return
     */
    @PutMapping
    public JsonResult modify(@RequestParam UmsUserAddressSaveVo umsUserAddressSaveVo){
        umsUserAddressService.modify(umsUserAddressSaveVo);
        return success();
    }

    /**
     * @description 删除地址
     * @param ids
     * @return
     */
    @DeleteMapping
    public JsonResult disable(@RequestParam String ids){
        umsUserAddressService.disable(ids);
        return success();
    }
}
