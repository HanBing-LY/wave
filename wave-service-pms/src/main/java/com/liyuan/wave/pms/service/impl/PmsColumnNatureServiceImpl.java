package com.liyuan.wave.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liyuan.wave.pms.common.PmsExceptionCode;
import com.liyuan.wave.pms.po.dto.PmsColumnNatureDto;
import com.liyuan.wave.po.pms.vo.PmsColumnNatureSaveVo;
import com.liyuan.wave.common.constant.CommonParam;
import com.liyuan.wave.common.exception.ExceptionCast;
import com.liyuan.wave.common.util.StringUtils;
import com.liyuan.wave.common.vo.response.PageInfo;
import com.liyuan.wave.po.pms.PmsColumnNature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.pms.mapper.PmsColumnNatureMapper;
import com.liyuan.wave.pms.service.PmsColumnNatureService;

import java.util.ArrayList;
import java.util.List;

/**
 * @description pms_column_nature
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Service("pmsColumnNatureService")
public class PmsColumnNatureServiceImpl extends ServiceImpl<PmsColumnNatureMapper, PmsColumnNature> implements PmsColumnNatureService {

    @Autowired
    PmsColumnNatureMapper pmsColumnNatureMapper;

    @Override
    public void add(PmsColumnNatureSaveVo pmsColumnNatureSaveVo) {
        Long productColumnId = pmsColumnNatureSaveVo.getProductColumnId();
        List<String> columnNatureNames = pmsColumnNatureSaveVo.getColumnNatureNames();
        List<PmsColumnNature> pmsColumnNatures = new ArrayList<>();
        columnNatureNames.forEach(i ->{
            PmsColumnNature pmsColumnNature = new PmsColumnNature();
            pmsColumnNature.setNatureName(i);
            pmsColumnNature.setProductColumnId(productColumnId);
            pmsColumnNatures.add(pmsColumnNature);
        });
        this.saveBatch(pmsColumnNatures);
    }

    @Override
    public void modify(PmsColumnNatureSaveVo pmsColumnNatureSaveVo) {
        PmsColumnNature pmsColumnNature = new PmsColumnNature();
        pmsColumnNature.setId(pmsColumnNatureSaveVo.getId());
        pmsColumnNature.setNatureName(pmsColumnNatureSaveVo.getName());
        pmsColumnNatureMapper.updateById(pmsColumnNature);
    }

    @Override
    public void disable(String ids) {
        List<Long> idList = StringUtils.stringToLongList(ids);
        if(idList.size() == 0){
            ExceptionCast.cast(PmsExceptionCode.PLEASE_CHO0SE_TO_DELETE);
        }
        PmsColumnNature pmsColumnNature = new PmsColumnNature();
        pmsColumnNature.setDel(CommonParam.IS_DELETED);
        QueryWrapper<PmsColumnNature> pmsColumnNatureQueryWrapper = new QueryWrapper<>();
        pmsColumnNatureQueryWrapper.in("id",idList);
        pmsColumnNatureMapper.update(pmsColumnNature,pmsColumnNatureQueryWrapper);
    }

    @Override
    public PageInfo<PmsColumnNatureDto> queryByColumnId(Long id, Long pageNo, Long pageSize) {
        List<PmsColumnNatureDto> pmsColumnNatureDtos = pmsColumnNatureMapper.queryByColumnId(id,pageNo,pageSize);
        PageInfo<PmsColumnNatureDto> pageInfo = new PageInfo<>();
        pageInfo.setList(pmsColumnNatureDtos);
        return pageInfo;
    }

}