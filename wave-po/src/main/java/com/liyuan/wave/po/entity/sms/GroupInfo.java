package com.liyuan.wave.po.entity.sms;


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
 * @description 拼团信息
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@TableName("group_info")
@NoArgsConstructor
@AllArgsConstructor
public class GroupInfo extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 团编码，唯一标识
     */
    private String groupNumber;
    /**
     * 拼团活动商品id
     */
    private Long groupSaleProductId;
    /**
     * 成团人数
     */
    private Long groupPeople;
    /**
     * 当前人数
     */
    private Long people;
    /**
     * 发起人
     */
    private Long userId;
    /**
     * 团状态 0:进行中  1:已成功  2:组团失败
     */
    private Byte status;
}

