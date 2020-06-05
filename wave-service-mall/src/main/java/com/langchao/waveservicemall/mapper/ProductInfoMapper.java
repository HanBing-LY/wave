package com.langchao.waveservicemall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.waveservicemall.pojo.ProductInfo;
import com.langchao.waveservicemall.pojo.dto.ProductInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductInfoMapper extends BaseMapper<ProductInfo> {

    List<ProductInfo> getCollectListByUserId(Integer userId);

    /**
     * @param map
     * @return java.util.List<com.chemguan.entity.ProductInfo>
     * @Author zzz
     * @Description //TODO 4-0-7 负责人权益-商品提成比例
     * @Date 15:08 2020/3/11
     **/
    List<ProductInfo> getList(Map map);

    /**
     * @param productInfoId
     * @return
     * @Author liyuan
     * 判断商品是否可以拼团
     * 返回值 大于0 有拼团计划
     */
    Integer judgeProductGroupType(Integer productInfoId);

    /**
     * @param productInfoId
     * @return
     * @Author liyuan
     * 判断商品是否可以秒杀
     * 返回值 大于0 有秒杀计划
     */
    Integer judgeProductFlashType(Integer productInfoId);


    /**
     * @param superType superType = 0 不开启超级拼购
     *                  superType = 1 开启超级拼购
     * @return
     * @Author liyuan
     * 返回所有拼团的产品id(未去重!!!)
     */
    List<Integer> listGetAllGroupSaleIds(Integer superType);

    /**
     * @param groupSaleProductIds id集合
     * @param groupColumn         排序列名
     * @param groupEsc            正倒排序
     * @return
     * @Author liyuan
     * 根据id集合查商品
     */
    List<ProductInfo> listGetProductsByIds(@Param("idslist") List<Integer> groupSaleProductIds, @Param("groupColumn") String groupColumn, @Param("groupEsc") String groupEsc);


    /**
     * @Description 后台list
     * @Author Renjinliang
     * @date 2020/3/23 11:30
     */
    List<ProductInfoDTO> findManagerList(Map map);

    /**
     * @Description 根据id查询详情
     * @Author Renjinliang
     * @date 2020/3/23 13:47
     */
    ProductInfoDTO findManagerById(Integer id);

    /**
     * 修改商品销量
     *
     * @param productId
     */
    void updateSaleNum(@Param("productId") Integer productId, @Param("productNum") Integer productNum);

    /**
     * @param
     * @return
     * @description 获取参与指定秒杀活动的所有商品
     **/
    List<ProductInfoDTO> getAllFlashSaleProduct(Integer flashSaleId);
    /**
     * @description 通关关键字搜索商品
     * @param
     * @return
     **/
    List<ProductInfo> getProductsByKeywords(String keywords);

    /**
     * 根据分类id查询产品数量
     *
     * @param columnId
     * @return
     */
    Integer findCountByColumnId(Integer columnId);

    void updateByVal(@Param("orderVal") Integer orderVal, @Param("id") Integer id);

    void updateType(@Param("id") Integer id, @Param("type") Integer type);
}