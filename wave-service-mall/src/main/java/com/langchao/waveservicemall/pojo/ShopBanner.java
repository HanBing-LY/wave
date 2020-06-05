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
 * @Title: ShopBanner
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:12 CST 2019
 */
@Data
@TableName("shop_banner")
@NoArgsConstructor
@AllArgsConstructor
public class ShopBanner extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 图片
     */
    private String bannerImg;
    /**
     * 链接
     */
    private String bannerUrl;

}

