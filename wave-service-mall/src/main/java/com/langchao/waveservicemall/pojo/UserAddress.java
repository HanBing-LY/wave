package com.langchao.waveservicemall.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.langchao.waveservicemall.pojo.base.BasicPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author
 * @Title: UserAddress
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:12 CST 2019
 */
@Data
@TableName("user_address")
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
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
    private Integer flag;

}

