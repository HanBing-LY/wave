package com.liyuan.wave.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.common.exception.ExceptionCast;
import com.liyuan.wave.common.util.StringUtils;
import com.liyuan.wave.pms.common.PmsExceptionCode;
import com.liyuan.wave.pms.constant.SelfCommonParam;
import com.liyuan.wave.pms.mapper.PmsProductInfoMapper;
import com.liyuan.wave.pms.mapper.PmsProductSkuMapper;
import com.liyuan.wave.pms.po.dto.PmsProductSkuDto;
import com.liyuan.wave.pms.service.PmsProductSkuService;
import com.liyuan.wave.po.pms.PmsProductInfo;
import com.liyuan.wave.po.pms.PmsProductSku;
import com.liyuan.wave.po.pms.vo.PmsProductSkuVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liyuan
 * @description pms_product_sku
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Service("pmsProductSkuService")
public class PmsProductSkuServiceImpl extends ServiceImpl<PmsProductSkuMapper, PmsProductSku> implements PmsProductSkuService {

    @Autowired
    private PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    private PmsProductSkuMapper pmsProductSkuMapper;


    @Override
    public PmsProductSkuVo getDetailByArticleNumber(String articleNumber) {
        PmsProductSkuDto pmsProductSkuDto = pmsProductSkuMapper.selectByArticleNumber(articleNumber);
        if (StringUtils.isNull(pmsProductSkuDto)) {
            ExceptionCast.cast(PmsExceptionCode.PRODUCT_SKU_NOT_EXIST);
        }
        Long productId = pmsProductSkuDto.getProductId();
        PmsProductInfo pmsProductInfo = pmsProductInfoMapper.selectById(productId);
        if (StringUtils.isNull(pmsProductInfo)) {
            ExceptionCast.cast(PmsExceptionCode.PRODUCT_SKU_NOT_EXIST);
        }
        if (SelfCommonParam.IS_SHELVES.equals(pmsProductInfo.getType())) {
            ExceptionCast.cast(PmsExceptionCode.PRODUCT_IS_SHELVES);
        }

        PmsProductSkuVo pmsProductSkuVo = new PmsProductSkuVo();
        BeanUtils.copyProperties(pmsProductInfo,pmsProductSkuVo);
        pmsProductSkuVo.setId(pmsProductSkuDto.getId());
        pmsProductSkuVo.setSkuSaleNum(pmsProductSkuDto.getSaleNum());
        pmsProductSkuVo.setSkuPrice(pmsProductSkuDto.getProductPrice());
        pmsProductSkuVo.setArticleNumber(articleNumber);
        return pmsProductSkuVo;
    }
}