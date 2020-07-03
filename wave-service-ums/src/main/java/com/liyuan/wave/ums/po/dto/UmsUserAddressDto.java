package com.liyuan.wave.ums.po.dto;

import lombok.Data;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020-07-03 16:12     
 */
@Data
public class UmsUserAddressDto {

    /**
     * id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 收货人姓名
     */
    private String userName;
    /**
     * 联系电话
     */
    private String userPhone;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String district;
    /**
     * 区
     */
    private String area;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 1:默认 0:非默认
     */
    private Byte flag;
}
