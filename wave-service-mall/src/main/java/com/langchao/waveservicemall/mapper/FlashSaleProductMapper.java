package com.langchao.waveservicemall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.waveservicemall.pojo.FlashSaleProduct;
import com.langchao.waveservicemall.pojo.dto.FlashSaleProductDTO;
import com.langchao.waveservicemall.pojo.vo.FlashSaleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface FlashSaleProductMapper extends BaseMapper<FlashSaleProduct> {

    /**
     * @Author liyuan
     * @Description 根据产品id查询商品的秒杀活动
     * @param productId
     * @return
     */
    List<FlashSaleProductDTO> getDetailByProductId(Integer productId);

    /**
     * @author liyuan
     * @Description 分页查询控制器
     * @param flag
     * @param startTime
     * @param endTime
     * @return
     */
    List<FlashSaleVo> list(@Param("flashSaleId") Integer flashSaleId, @Param("productName") String productName, @Param("flag") Integer flag, @Param("startTime") Date startTime, @Param("endTime") Date endTime);


    /**
     * @author liyuan
     * @Description 删除商品秒杀
     * @param flashSaleProductIds
     */
    void deleteByProductSkuIds(@Param("flashSaleProductIds") List<Integer> flashSaleProductIds);

    /**
     * @author liyuan
     * @Description 根据秒杀控制删除关联信息
     * @param flashSaleIds
     */
    void deleteByFlashSaleIds(@Param("flashSaleIds") List<Integer> flashSaleIds);
}