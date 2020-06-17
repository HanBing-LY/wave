package com.liyuan.wave.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.pms.common.PmsProductColumnCode;
import com.liyuan.wave.pms.mapper.PmsProductColumnMapper;
import com.liyuan.wave.pms.po.dto.PmsProductColumnDto;
import com.liyuan.wave.pms.po.vo.PmsProductColumnSaveVo;
import com.liyuan.wave.pms.po.vo.PmsProductColumnVo;
import com.liyuan.wave.pms.service.PmsProductColumnService;
import com.liyuan.wave.po.pms.PmsProductColumn;
import com.liyuan.wavecommon.constant.CommonParam;
import com.liyuan.wavecommon.exception.ExceptionCast;
import com.liyuan.wavecommon.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author liyuan
 * @description pms_product_column
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Service("pmsProductColumnService")
public class PmsProductColumnServiceImpl extends ServiceImpl<PmsProductColumnMapper, PmsProductColumn> implements PmsProductColumnService {

    @Autowired
    private PmsProductColumnMapper pmsProductColumnMapper;

    /**
     * @param productColumnSaveVo 交互待保存对象
     * @return
     * @description 新增商品分类
     */
    @Override
    public void addProductColumn(PmsProductColumnSaveVo productColumnSaveVo) {
        PmsProductColumn pmsProductColumn = new PmsProductColumn();
        BeanUtils.copyProperties(productColumnSaveVo, pmsProductColumn);
        pmsProductColumn.setId(null);
        Long parentId = productColumnSaveVo.getParentId();

        if (parentId != null && parentId > 0L) {
            // 选择了父节点
            List<PmsProductColumnDto> pmsProductColumnDtos = pmsProductColumnMapper.getCommonDetailById(parentId);
            if (pmsProductColumnDtos.size() == 0) {
                ExceptionCast.cast(PmsProductColumnCode.PARENT_NOT_EXIST);
            }
            PmsProductColumnDto parent = pmsProductColumnDtos.get(0);
            String parentIds = parent.getParentIds();
            parentIds = Optional.ofNullable(parentIds).orElse("");
            Long columnLevel = parent.getColumnLevel();
            if (StringUtils.isNotNull(columnLevel) && columnLevel.equals(1L)) {
                // 设为二级分类
                pmsProductColumn.setColumnLevel(2L);
                pmsProductColumn.setParentIds(parentId.toString());
            } else {
                // 设为下一级分类
                pmsProductColumn.setColumnLevel(columnLevel + 1L);
                pmsProductColumn.setParentIds(parentIds + "-" + parentId);
            }
            return;
        } else {
            // 设为一级分类
            pmsProductColumn.setColumnLevel(1L);
        }
        pmsProductColumnMapper.insert(pmsProductColumn);
    }

    /**
     * @description 保存商品分类名称和图片url
     * @param pmsProductColumnSaveVo
     * @return
     */
    @Override
    public void updateProductColumn(PmsProductColumnSaveVo pmsProductColumnSaveVo) {
        PmsProductColumn pmsProductColumn = new PmsProductColumn();
        pmsProductColumn.setId(pmsProductColumnSaveVo.getId());
        pmsProductColumn.setColumnName(pmsProductColumnSaveVo.getColumnName());
        pmsProductColumn.setColumnImage(pmsProductColumnSaveVo.getColumnImage());
        pmsProductColumnMapper.updateById(pmsProductColumn);
    }

    /**
     * @description 禁用商品分类
     * @param ids
     * @return
     */
    @Override
    public void disabled(String ids) {
        List<Long> idList = StringUtils.stringToLongList(ids);
        if(idList.size() == 0){
            ExceptionCast.cast(PmsProductColumnCode.PLEASE_CHO0SE_TO_DELETE);
        }
        PmsProductColumn pmsProductColumn = new PmsProductColumn();
        pmsProductColumn.setDel(CommonParam.IS_DELETED);
        QueryWrapper<PmsProductColumn> pmsProductColumnQueryWrapper = new QueryWrapper<>();
        pmsProductColumnQueryWrapper.in("id",idList);
        pmsProductColumnMapper.update(pmsProductColumn,pmsProductColumnQueryWrapper);
    }

    /**
     * @description 查看某一级的所有分类
     * @param id
     * @return
     */
    @Override
    public List<PmsProductColumnVo> getNextChildrenByParentId(Long id) {
        id = Optional.ofNullable(id).orElse(0L);
        List<PmsProductColumnDto> nextChildrenByParentId = pmsProductColumnMapper.getNextChildrenByParentId(id);

        List<PmsProductColumnVo> pmsProductColumnVoList = new ArrayList<>();

        nextChildrenByParentId.forEach(i->{
            PmsProductColumnVo pmsProductColumnVo = new PmsProductColumnVo();
            BeanUtils.copyProperties(i,pmsProductColumnVo);
            pmsProductColumnVoList.add(pmsProductColumnVo);
        });

        return pmsProductColumnVoList;
    }
}