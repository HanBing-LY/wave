package com.langchao.waveservicemall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.waveservicemall.pojo.ProductColumn;
import com.langchao.waveservicemall.pojo.dto.ProductColumnDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductColumnMapper extends BaseMapper<ProductColumn> {

    /**
     *@Description 后台list
     *@Author Renjinliang
     *@date 2020/3/20 16:07
     */
    List<ProductColumnDto> findManagerList();
    /**
     *@Description 二级分类
     *@Author Renjinliang
     *@date 2020/3/24 13:58
     */
    List<ProductColumnDto> findManagerListById(Map map);

    /**
     *@Description 根据id查询商品分类，为0则查询所有
     *@Author ws
     *@date 2020/3/25 13:58
     */
    List<ProductColumnDto> getProductColumnsList(@Param("columnId") Integer columnId);


    /**
     * 根据分类id查询下面自分类
     * @param columnId
     * @return
     */
    List<ProductColumn> findbyparent(Integer columnId);
}