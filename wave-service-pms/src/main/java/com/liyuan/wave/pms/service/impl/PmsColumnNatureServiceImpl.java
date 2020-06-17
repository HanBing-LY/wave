package com.liyuan.wave.pms.service.impl;

import com.liyuan.wave.pms.po.dto.PmsColumnNatureDto;
import com.liyuan.wave.pms.po.vo.PmsColumnNatureVo;
import com.liyuan.wavecommon.vo.response.PageInfo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.pms.mapper.PmsColumnNatureMapper;
import com.liyuan.wave.po.pms.PmsColumnNature;
import com.liyuan.wave.pms.service.PmsColumnNatureService;

/**
 * @description pms_column_nature
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Service("pmsColumnNatureService")
public class PmsColumnNatureServiceImpl extends ServiceImpl<PmsColumnNatureMapper, PmsColumnNature> implements PmsColumnNatureService {

    @Override
    public void add(PmsColumnNatureDto pmsColumnNatureDto) {

    }

    @Override
    public void modify(PmsColumnNature pmsColumnNature) {

    }

    @Override
    public void disable(String ids) {

    }

    @Override
    public PageInfo<PmsColumnNatureVo> queryByColumnId(String id) {
        return null;
    }

}