package com.liyuan.wave.ums.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.po.ums.UmsUserAddress;
import com.liyuan.wave.po.ums.vo.UmsUserAddressVo;
import com.liyuan.wave.ums.po.dto.UmsUserAddressSaveVo;

import java.util.List;

/**
 * @author liyuan
 * @description
 * @date 2020-06-15 15:46
 */
public interface UmsUserAddressService extends IService<UmsUserAddress> {

    /**
     * @description 查询用户的地址
     * @param userId
     * @return
     */
    List<UmsUserAddressVo> queryByUserId(Long userId);

    /**
     * @description 新添地址
     * @param umsUserAddressSaveVo
     * @return
     */
    void create(UmsUserAddressSaveVo umsUserAddressSaveVo);

    /**
     * @description 修改地址
     * @param umsUserAddressSaveVo
     * @return
     */
    void modify(UmsUserAddressSaveVo umsUserAddressSaveVo);

    /**
     * @description 删除地址
     * @param ids
     * @return
     */
    void disable(String ids);
}
