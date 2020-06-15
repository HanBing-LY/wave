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
 * @description 银行卡信息
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@TableName("ums_user_bank")
@NoArgsConstructor
@AllArgsConstructor
public class UmsUserBank extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行账号
     */
    private String bankAccount;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 户名
     */
    private String userName;
}

