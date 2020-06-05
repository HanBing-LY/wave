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
 * @Title: ProductEvaluate
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:11 CST 2019
 */
@Data
@TableName("product_evaluate")
@NoArgsConstructor
@AllArgsConstructor
public class ProductEvaluate extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 产品id
     */
    private Integer productId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 评价内容
     */
    private String evaluateDesc;
    /**
     * 评价图片
     */
    private String evaluateImg;
    /**
     * 评价星级
     */
    private Integer star;
    /**
     * 添加时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;

}

