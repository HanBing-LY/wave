package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.langchao.waveservicemall.pojo.GroupSaleProductSku;
import com.langchao.waveservicemall.pojo.dto.ColumnNatureDTO;
import com.langchao.waveservicemall.pojo.vo.GroupSaleProductSkuVo;

import java.util.List;

/**
 * @Title: GroupSaleProductSkuService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 17:10:28 CST 2020
 */
public interface GroupSaleProductSkuService  extends IService<GroupSaleProductSku> {

   IPage list(Integer groupSaleProductId, Integer page, Integer size);

    /**
     * @Author liyuan
     * @Description 分页查询拼团控制
     * @param productId
     * @return
     */
    List<ColumnNatureDTO> listGetNatureToChoose(Integer productId);

    /**
     * @author liyuan
     * @Description  停止商品拼团
     * @return
     */
    void delpl(String checkIds);

    /**
     * @Author liyuan
     * @Description 修改拼团价
     * @return
     */
    void updatePrice(Integer groupSaleProductSkuId, Double price);

    /**
     * @Author liyuan
     * @Description 选择拼团商品
     * @return
     */
    void chooseProduct(GroupSaleProductSkuVo groupSaleProductSkuVo);
}
