package com.langchao.waveservicemall.pojo.dto;

import com.langchao.waveservicemall.pojo.ProductEvaluate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liyuan
 * @create 2020-03-19-19:16-周四
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEvaluateDTO extends ProductEvaluate {

    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userImg;
    /**
     * 好评率
     */
    private String positiveRating;
    /**
     * 评论集合
     */
    private List<ProductEvaluateDTO> productEvaluateDTOList;
}
