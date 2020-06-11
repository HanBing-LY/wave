package com.liyuan.wave.sms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.wavecommon.to.MemberPrice;
import com.langchao.wavecommon.to.SkuReductionTo;
import com.liyuan.wave.po.entity.sms.MemberPriceEntity;
import com.liyuan.wave.po.entity.sms.SkuFullReductionEntity;
import com.liyuan.wave.po.entity.sms.SkuLadderEntity;
import com.liyuan.wave.sms.mapper.SkuFullReductionDao;
import com.liyuan.wave.sms.service.SkuFullReductionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:46
 */
@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {
}