package com.langchao.waveservicemall.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.langchao.waveservicemall.pojo.base.BasicPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @Title: GroupInfo
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:10 CST 2019
 */

@Data
@TableName("group_info")
@NoArgsConstructor
@AllArgsConstructor
public class GroupInfo extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 团编码，唯一标识
     */
    private Integer groupNumber;
    /**
     * 拼团活动id
     */
    private Integer groupSaleId;
    /**
     * 俱乐部id
     */
    private Integer clubId;
    /**
     * 产品id
     */
    private Integer productId;
    /**
     * 成团人数
     */
    private Integer groupPeople;
    /**
     * 当前人数
     */
    private Integer people;
    /**
     * 开团时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;
    /**
     * 1:已成功 0:未成功
     */
    private Integer type;

}

