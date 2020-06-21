package com.liyuan.wave.wms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.common.exception.ExceptionCast;
import com.liyuan.wave.common.util.StringUtils;
import com.liyuan.wave.po.wms.WmsWareSku;
import com.liyuan.wave.po.wms.vo.WmsWareSkuSaveVo;
import com.liyuan.wave.po.wms.vo.WmsWareSkuVo;
import com.liyuan.wave.wms.common.WmsExceptionCode;
import com.liyuan.wave.wms.mapper.WmsWareSkuMapper;
import com.liyuan.wave.wms.po.dto.WmsWareSkuDto;
import com.liyuan.wave.wms.service.WmsWareSkuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liyuan
 * @description
 * @date 2020-06-15 15:46
 */
@Service
public class WmsWareSkuServiceImpl extends ServiceImpl<WmsWareSkuMapper, WmsWareSku> implements WmsWareSkuService {

    @Autowired
    private WmsWareSkuMapper wmsWareSkuMapper;

    @Override
    public WmsWareSkuVo queryByArticleNumber(String articleNumber) {
        if(StringUtils.isEmpty(articleNumber)){
            return null;
        }
        WmsWareSkuDto wmsWareSkuDto = wmsWareSkuMapper.selectByArticleNumber(articleNumber);
        WmsWareSkuVo wmsWareSkuVo = new WmsWareSkuVo();
        BeanUtils.copyProperties(wmsWareSkuDto,wmsWareSkuVo);
        return wmsWareSkuVo;
    }

    /**
     * @description 扣减商品库存
     * @param wmsWareSkuSaveVo
     * @return
     */
    @Override
    public void decrement(WmsWareSkuSaveVo wmsWareSkuSaveVo) {
        String articleNumber = wmsWareSkuSaveVo.getArticleNumber();
        Long stockToDecrement = wmsWareSkuSaveVo.getStock();
        if(stockToDecrement < 0L){
            ExceptionCast.cast(WmsExceptionCode.WARE_SKU_TO_DECREMENT_IS_ERROR);
        }
        WmsWareSkuDto wmsWareSkuDto = wmsWareSkuMapper.selectByArticleNumber(articleNumber);
        if(StringUtils.isNull(wmsWareSkuDto)){
            ExceptionCast.cast(WmsExceptionCode.WARE_SKU_SUFFICIENT);
        }
        Long stock = wmsWareSkuDto.getStock();
        if(stock < stockToDecrement){
            ExceptionCast.cast(WmsExceptionCode.WARE_SKU_SUFFICIENT);
        }
        Integer count = wmsWareSkuMapper.decrementStock(articleNumber,stockToDecrement);
        if(count == null || count == 0L){
            ExceptionCast.cast(WmsExceptionCode.WARE_SKU_SUFFICIENT);
        }
    }
}
