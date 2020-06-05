package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.langchao.waveservicemall.pojo.ProductInfo;
import com.langchao.waveservicemall.pojo.dto.ProductInfoDTO;
import com.langchao.waveservicemall.pojo.vo.ColumnProductVo;
import com.langchao.waveservicemall.pojo.vo.ScoreProductInfoVo;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @Title: ProductInfoService
 * @ProjectName
 * @Description: TODO
 * @date Mon Feb 17 16:34:44 CST 2020
 */
public interface ProductInfoService extends IService<ProductInfo> {


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
     * 1-0-0查询热销商品
     *
     * @return
     * @Author liyuan
     */
    IPage listHotSaleList(Integer pageNum, Integer pageSize);

    /**
     * 1-0-0查询最新商品
     *
     * @return
     * @Author liyuan
     */
    IPage listNewPushList(Integer pageNum, Integer pageSize);

    /**
     * 1-0-1-0-0 俱乐部拼团 参与拼团列表,返回6条数据
     *
     * @param orderType 0:默认，按照商品排序值排序
     *                  1:按商品销量由高到低排序
     *                  2:按照商品销量由低到高排序
     *                  3:按照价格由高到低排序
     *                  4:按照价格由低到高排序
     * @return
     * @Author liyuan
     */
    IPage listGroupSaleList(Integer orderType, Integer pageNum, Integer pageSize);

    /**
     * @return
     * @Author liyuan
     * 1-0-1-0-1	超级拼购
     */
    IPage listSuperGroupSaleList(Integer pageNum, Integer pageSize);

    /**
     * @param productId 商品id
     * @return
     * @Author liyuan
     * 1-0-1-1-0 俱乐部拼购-商品详情
     */
    ProductInfoDTO getProductDetailByid(Integer productId);

    /**
     * @Description 后台list
     * @Author Renjinliang
     * @date 2020/3/23 11:32
     */
    IPage findManagerList(Integer pageNum, Integer pageSize, Integer columnName, String productName, Integer type);

    /**
     * @Description 后台根据id查询详情
     * @Author Renjinliang
     * @date 2020/3/23 13:48
     */
    ProductInfoDTO findManagerById(Integer id);

    /**
     * @return
     * @Author ws
     * @description 1-0-2-0-0 积分商品列表
     */
    IPage getScoreProductList(Integer pageNum, Integer pageSize);

    /**
     * @return
     * @Author ws
     * @description 1-0-2-2-0 积分商品详情
     */
    ScoreProductInfoVo getScoreProductInfo(Integer productId);


    /**
     * @param
     * @return
     * @description 分页查询正在进行秒杀的商品列表
     **/
    Map getNowFlashSaleList(Integer pageNum, Integer pageSize);

    /**
     * @param
     * @return
     * @description 分页查询正在进行秒杀的商品列表
     **/
    Map getNextFlashSaleList(Integer pageNum, Integer pageSize);

    /**
     * @param
     * @return
     * @description 1-1-0-2商品分类 商品列表
     **/
    List<ColumnProductVo> getProductsByColumnId(Integer columnId, Integer orderType, Integer pageNum, Integer pageSize);

    /**
     * @param
     * @return
     * @description 1-1-0-2商品分类 商品列表
     **/
    IPage getProductsbySearch(String keywords, Integer pageNum, Integer pageSize);


    /**
     * 根据分类id查询产品数量
     *
     * @param columnId
     * @return
     */
    Integer findCountByColumnId(Integer columnId);

    /**
     * @Description 修改多条数据的排序值
     * @Author Renjinliang
     * @date 2020/3/27 16:55
     */
    void updateByOrderVal(String ids, String orderVal);

    /**
     * @Description 修改上架状态
     * @Author Renjinliang
     * @date 2020/3/27 20:36
     */
    void updateType(String ids, Integer type);
}
