package com.liyuan.wave.ums.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyuan.wave.po.ums.UmsUserAddress;
import com.liyuan.wave.ums.po.dto.UmsUserAddressDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liyuan
 * @description
 * @date 2020-06-15 15:44
 */
@Mapper
public interface UmsUserAddressMapper extends BaseMapper<UmsUserAddress> {

    /**
     * @description 判断用户是否有地址
     * @param userId
     * @return
     */
    Long checkUserHasAddress(@Param("userId") Long userId);

    /**
     * @description 判断用户是否有默认地址
     * @param idList
     * @return
     */
    Long checkHasCommonAddress(@Param("idList") List<Long> idList);

    /**
     * @description 默认地址改为非默认
     * @param userId
     * @return
     */
    Integer clearCommonAddressByUserId(@Param("userId") Long userId);

    /**
     * @description 查询用户的收货地址
     * @param userId
     * @return
     */
    List<UmsUserAddressDto> selectByUserId(@Param("userId") Long userId);
}
