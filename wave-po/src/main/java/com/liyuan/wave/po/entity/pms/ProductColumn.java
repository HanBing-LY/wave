package com.liyuan.wave.po.entity.pms;


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
 * @description 商品分类
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@TableName("product_column")
@NoArgsConstructor
@AllArgsConstructor
public class ProductColumn extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 分类名称
     */
    private String columnName;
    /**
     * 分类图片
     */
    private String columnImage;
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 所有父节点id: 1-3-14
     */
    private String parentIds;
    /**
     * 分类等级
     */
    private Long columnLevel;
}

