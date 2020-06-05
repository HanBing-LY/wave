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
 * @Title: GroupInfoDetail
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:10 CST 2019
 */
@Data
@TableName("group_info_detail")
@NoArgsConstructor
@AllArgsConstructor
public class GroupInfoDetail extends BasicPo implements Serializable {

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
     * 拼团信息id
     */
    private Integer groupInfoId;
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 拼团时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;

}

