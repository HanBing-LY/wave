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
 * @description sku 属性值
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@TableName("nature_value")
@NoArgsConstructor
@AllArgsConstructor
public class NatureValue extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 属性id
     */
    private Long columnNatureId;

    /**
     * 属性值
     */
    private String valueDesc;

    /**
     * 属性选择类型：0->单选；1->多选
     */
    private Byte selectType;

}

