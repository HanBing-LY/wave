package com.liyuan.wave.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liyuan.wave.pms.common.PmsExceptionCode;
import com.liyuan.wave.pms.po.dto.PmsNatureValueDto;
import com.liyuan.wave.po.pms.vo.PmsNatureValueSaveVo;
import com.liyuan.wave.common.constant.CommonParam;
import com.liyuan.wave.common.exception.ExceptionCast;
import com.liyuan.wave.common.util.StringUtils;
import com.liyuan.wave.common.vo.response.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.pms.mapper.PmsNatureValueMapper;
import com.liyuan.wave.po.pms.PmsNatureValue;
import com.liyuan.wave.pms.service.PmsNatureValueService;

import java.util.ArrayList;
import java.util.List;

/**
 * @description pms_nature_value
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Service("pmsNatureValueService")
public class PmsNatureValueServiceImpl extends ServiceImpl<PmsNatureValueMapper, PmsNatureValue> implements PmsNatureValueService {

    @Autowired
    private PmsNatureValueMapper pmsNatureValueMapper;

    @Override
    public void add(PmsNatureValueSaveVo pmsNatureValueVo) {
        Long columnNatureId = pmsNatureValueVo.getColumnNatureId();
        List<String> columnNatureNames = pmsNatureValueVo.getNames();
        List<PmsNatureValue> pmsNatureValues = new ArrayList<>();
        columnNatureNames.forEach(i ->{
            PmsNatureValue pmsNatureValue = new PmsNatureValue();
            pmsNatureValue.setValueDesc(i);
            pmsNatureValue.setColumnNatureId(columnNatureId);
            pmsNatureValues.add(pmsNatureValue);
        });
        this.saveBatch(pmsNatureValues);
    }

    @Override
    public void modify(PmsNatureValueSaveVo pmsNatureValueVo) {
        PmsNatureValue pmsNatureValue = new PmsNatureValue();
        pmsNatureValue.setId(pmsNatureValueVo.getId());
        pmsNatureValue.setValueDesc(pmsNatureValueVo.getName());
        pmsNatureValueMapper.updateById(pmsNatureValue);
    }

    @Override
    public void disable(String ids) {
        List<Long> idList = StringUtils.stringToLongList(ids);
        if(idList.size() == 0){
            ExceptionCast.cast(PmsExceptionCode.PLEASE_CHO0SE_TO_DELETE);
        }
        PmsNatureValue pmsNatureValue = new PmsNatureValue();
        pmsNatureValue.setDel(CommonParam.IS_DELETED);
        QueryWrapper<PmsNatureValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",idList);
        pmsNatureValueMapper.update(pmsNatureValue,queryWrapper);
    }

    @Override
    public PageInfo<PmsNatureValueDto> queryByColumnNatureId(Long id, Long pageNum, Long pageSize) {
        long start = (pageNum - 1) * pageSize;
        long end = pageNum * pageSize;
        List<PmsNatureValueDto> pmsNatureValueDtoList = pmsNatureValueMapper.queryByColumnNatureId(id,start,end);
        PageInfo<PmsNatureValueDto> pageInfo = new PageInfo<>();
        pageInfo.setList(pmsNatureValueDtoList);
        return pageInfo;
    }
}