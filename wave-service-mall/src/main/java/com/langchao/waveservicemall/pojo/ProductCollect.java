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
 * @Title: ProductCollect
 * @ProjectName
 * @Description: TODO
 * @date Mon Feb 17 16:34:44 CST 2020
 */
@Data
@TableName("product_collect")
@NoArgsConstructor
@AllArgsConstructor
public class ProductCollect extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 添加时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;

}

