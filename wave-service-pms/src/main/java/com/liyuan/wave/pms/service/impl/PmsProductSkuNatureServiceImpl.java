package com.liyuan.wave.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.common.cache.constant.ProductCache;
import com.liyuan.wave.common.util.StringUtils;
import com.liyuan.wave.pms.mapper.PmsProductSkuMapper;
import com.liyuan.wave.pms.mapper.PmsProductSkuNatureMapper;
import com.liyuan.wave.po.pms.vo.PmsNatureValueVo;
import com.liyuan.wave.po.pms.vo.PmsProductSkuNatureVo;
import com.liyuan.wave.pms.service.PmsProductSkuNatureService;
import com.liyuan.wave.po.pms.PmsProductSku;
import com.liyuan.wave.po.pms.PmsProductSkuNature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description pms_product_sku_nature
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Service("pmsProductSkuNatureService")
public class PmsProductSkuNatureServiceImpl extends ServiceImpl<PmsProductSkuNatureMapper, PmsProductSkuNature> implements PmsProductSkuNatureService {

    @Autowired
    private PmsProductSkuMapper pmsProductSkuMapper;
    @Autowired
    private PmsProductSkuNatureMapper pmsProductSkuNatureMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * @description 商品选择具有的sku属性值
     * @param pmsProductSkuNatureVo
     * @return
     */
    @Override
    public void saveProductSkuNatureValue(PmsProductSkuNatureVo pmsProductSkuNatureVo) {
        Long productInfoId = pmsProductSkuNatureVo.getProductInfoId();
        BigDecimal price = pmsProductSkuNatureVo.getPrice();
        List<PmsNatureValueVo> natureValueVos = pmsProductSkuNatureVo.getNatureValueVos();
        PmsProductSku pmsProductSku = new PmsProductSku();
        pmsProductSku.setProductId(productInfoId);
        pmsProductSku.setProductPrice(price);
        Long increment = stringRedisTemplate.opsForValue().increment(ProductCache.PRODUCT_NUMBER);
        if(StringUtils.isNull(increment)){
            increment = Instant.now().getEpochSecond();
        }
        pmsProductSku.setArticleNumber(increment.toString());
        pmsProductSkuMapper.insert(pmsProductSku);
        List<PmsProductSkuNature> pmsProductSkuNatureList = natureValueVos.stream().map(i -> {
            PmsProductSkuNature pmsProductSkuNature = new PmsProductSkuNature();
            pmsProductSkuNature.setColumnNatureId(i.getColumnNatureId());
            pmsProductSkuNature.setProductSkuId(pmsProductSku.getId());
            i.getNatureValueIds().forEach(pmsProductSkuNature::setNatureValueId);
            return pmsProductSkuNature;
        }).collect(Collectors.toList());
        this.saveBatch(pmsProductSkuNatureList);
    }
}