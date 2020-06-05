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
 * @Title: GroupSaleProduct
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:10 CST 2019
 */
@Data
@TableName("group_sale_product")
@NoArgsConstructor
@AllArgsConstructor
public class GroupSaleProduct extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 产品表id
     */
    private Integer productId;
    /**
     * 拼团活动表
     */
    private Integer groupSaleId;
    /**
     * 拼团价格
     */
    private Double price;
    /**
     * 拼团人数
     */
    private Integer groupPeople;
    /**
     * 总拼团人数
     */
    private Integer totalPeople;
    /**
     * 是否超级拼购 1:是 0:否
     */
    private Integer superType;

}

