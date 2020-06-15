package com.liyuan.wave.pms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.pms.dao.PmsNatureValueDao;
import com.liyuan.wave.po.entity.pms.PmsNatureValueEntity;
import com.liyuan.wave.pms.service.PmsNatureValueService;

/**
 * @description pms_nature_value
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Service("pmsNatureValueService")
public class PmsNatureValueServiceImpl extends ServiceImpl<PmsNatureValueDao, PmsNatureValueEntity> implements PmsNatureValueService {

}