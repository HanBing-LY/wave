package com.liyuan.wave.po.ums.vo;

import lombok.Data;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-21-21:04
 */
@Data
public class UmsUserAddressVo {

    /**
     * id
     */
    private Long id;
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
