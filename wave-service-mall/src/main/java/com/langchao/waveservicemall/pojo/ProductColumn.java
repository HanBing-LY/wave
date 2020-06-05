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
 * @Title: ProductColumn
 * @ProjectName 产品分类表
 * @Description: TODO
 * @date Thu Dec 26 09:52:11 CST 2019
 */
@Data
@TableName("product_column")
@NoArgsConstructor
@AllArgsConstructor
public class ProductColumn extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
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
    private Integer parentId;

    /**
     * 分类等级
     */
    private Integer columnLevel;

}

