package com.liyuan.wave.pms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.pms.mapper.PmsNatureValueMapper;
import com.liyuan.wave.po.pms.PmsNatureValue;
import com.liyuan.wave.pms.service.PmsNatureValueService;

/**
 * @description pms_nature_value
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Service("pmsNatureValueService")
public class PmsNatureValueServiceImpl extends ServiceImpl<PmsNatureValueMapper, PmsNatureValue> implements PmsNatureValueService {

}