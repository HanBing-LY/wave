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
 * @Title: NatureValue
 * @ProjectName 产品信息属性值表
 * @Description: TODO
 * @date Thu Dec 26 09:52:11 CST 2019
 */
@Data
@TableName("nature_value")
@NoArgsConstructor
@AllArgsConstructor
public class NatureValue extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 属性id
     */
    private Integer natureId;
    /**
     * 属性值
     */
    private String valueDesc;

}

