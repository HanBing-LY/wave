package com.liyuan.wave.po.entity.ums;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liyuan
 * @description 地址
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@TableName("user_address")
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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

