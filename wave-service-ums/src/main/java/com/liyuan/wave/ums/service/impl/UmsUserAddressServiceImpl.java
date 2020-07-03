package com.liyuan.wave.ums.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.common.constant.CommonParam;
import com.liyuan.wave.common.exception.ExceptionCast;
import com.liyuan.wave.common.util.StringUtils;
import com.liyuan.wave.po.ums.UmsUserAddress;
import com.liyuan.wave.po.ums.vo.UmsUserAddressVo;
import com.liyuan.wave.ums.common.UmsExceptionCode;
import com.liyuan.wave.ums.constant.SelfCommonParam;
import com.liyuan.wave.ums.mapper.UmsUserAddressMapper;
import com.liyuan.wave.ums.po.dto.UmsUserAddressDto;
import com.liyuan.wave.ums.po.dto.UmsUserAddressSaveVo;
import com.liyuan.wave.ums.service.UmsUserAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liyuan
 * @description
 * @date 2020-06-15 15:46
 */
@Service
public class UmsUserAddressServiceImpl extends ServiceImpl<UmsUserAddressMapper, UmsUserAddress> implements UmsUserAddressService {

    @Autowired
    private UmsUserAddressMapper umsUserAddressMapper;

    /**
     * @param userId
     * @return
     * @description 查询用户的地址
     */
    @Override
    public List<UmsUserAddressVo> queryByUserId(Long userId) {
        List<UmsUserAddressDto> umsUserAddressDtos = umsUserAddressMapper.selectByUserId(userId);
        return umsUserAddressDtos.stream().map(i -> {
            UmsUserAddressVo umsUserAddressVo = new UmsUserAddressVo();
            BeanUtils.copyProperties(i, umsUserAddressVo);
            return umsUserAddressVo;
        }).collect(Collectors.toList());
    }

    /**
     * @param umsUserAddressSaveVo
     * @return
     * @description 新添地址
     */
    @Override
    public void create(UmsUserAddressSaveVo umsUserAddressSaveVo) {
        UmsUserAddress umsUserAddress = new UmsUserAddress();
        BeanUtils.copyProperties(umsUserAddressSaveVo, umsUserAddress);
        Long userId = umsUserAddressSaveVo.getUserId();
        Long count = umsUserAddressMapper.checkUserHasAddress(userId);
        if (StringUtils.isNull(count) || count == 0L) {
            // 用户没有地址,设为默认地址
            umsUserAddress.setFlag(SelfCommonParam.ADDRESS_IS_COMMON);
        } else {
            umsUserAddress.setFlag(SelfCommonParam.ADDRESS_IS_NOT_COMMON);
        }
        umsUserAddress.setId(null);
        umsUserAddressMapper.insert(umsUserAddress);
    }

    /**
     * @param umsUserAddressSaveVo
     * @return
     * @description 修改地址
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(UmsUserAddressSaveVo umsUserAddressSaveVo) {
        UmsUserAddress umsUserAddress = new UmsUserAddress();
        BeanUtils.copyProperties(umsUserAddressSaveVo, umsUserAddress);
        Long userId = umsUserAddressSaveVo.getUserId();
        Byte newFlag = umsUserAddressSaveVo.getFlag();
        Long id = umsUserAddressSaveVo.getId();
        UmsUserAddress oldUmsUserAddress = umsUserAddressMapper.selectById(id);
        Byte oldFlag = oldUmsUserAddress.getFlag();
        if (SelfCommonParam.ADDRESS_IS_NOT_COMMON.equals(oldFlag) && SelfCommonParam.ADDRESS_IS_COMMON.equals(newFlag)) {
            // 原本非默认地址,现在改成默认地址
            Integer count = umsUserAddressMapper.clearCommonAddressByUserId(userId);
            if (count != 1) {
                ExceptionCast.cast(UmsExceptionCode.ADDRESS_UPDATE_ERROR);
            }
            umsUserAddress.setFlag(SelfCommonParam.ADDRESS_IS_COMMON);
        }
        if (SelfCommonParam.ADDRESS_IS_COMMON.equals(oldFlag) && SelfCommonParam.ADDRESS_IS_NOT_COMMON.equals(newFlag)) {
            // 原本默认地址,现在改成非默认地址;禁止修改,仍为默认
            umsUserAddress.setFlag(SelfCommonParam.ADDRESS_IS_COMMON);
        }
        umsUserAddressMapper.updateById(umsUserAddress);
    }

    /**
     * @param ids
     * @return
     * @description 删除地址
     */
    @Override
    public void disable(String ids) {
        List<Long> idList = StringUtils.stringToLongList(ids);
        if (idList.size() == 0) {
            ExceptionCast.cast(UmsExceptionCode.PLEASE_CHO0SE_TO_DELETE);
        }
        Long count = umsUserAddressMapper.checkHasCommonAddress(idList);
        if (count != null && count != 0) {
            ExceptionCast.cast(UmsExceptionCode.COMMON_ADDRESS_NOT_TO_DELETE);
        }
        UmsUserAddress umsUserAddress = new UmsUserAddress();
        umsUserAddress.setDel(CommonParam.IS_DELETED);
        QueryWrapper<UmsUserAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", idList);
        umsUserAddressMapper.update(umsUserAddress, queryWrapper);
    }
}
