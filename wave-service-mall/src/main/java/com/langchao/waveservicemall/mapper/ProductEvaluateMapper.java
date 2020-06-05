package com.langchao.waveservicemall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.langchao.waveservicemall.pojo.ProductEvaluate;
import com.langchao.waveservicemall.pojo.dto.ProductEvaluateDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductEvaluateMapper extends BaseMapper<ProductEvaluate> {

    /**
     * @Author liyuan
     * 根据评论id集合查对应的评论
     * @param productEvaluateIds
     * @return
     */
    List<ProductEvaluateDTO> commonSelectByIds(IPage<ProductEvaluateDTO> iPage,@Param("productEvaluateIds") List<Integer> productEvaluateIds);

    /**
     *@Description 后台list
     *@Author Renjinliang
     *@date 2020/3/25 16:57
     */
    List<ProductEvaluateDTO> findManagerList(Map map);
}