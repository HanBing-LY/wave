package com.liyuan.wave.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.pms.po.dto.PmsColumnNatureDto;
import com.liyuan.wave.pms.po.vo.PmsColumnNatureSaveVo;
import com.liyuan.wave.po.pms.PmsColumnNature;
import com.liyuan.wave.common.vo.response.PageInfo;

/**
 * @description pms_column_nature
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
public interface PmsColumnNatureService extends IService<PmsColumnNature> {
    /**
     * @description 新增通用分类属性
     * @param pmsColumnNatureSaveVo
     * @return
     */
    void add(PmsColumnNatureSaveVo pmsColumnNatureSaveVo);

    /**
     * @description 修改分类属性名称
     * @param pmsColumnNatureSaveVo
     * @return
     */
    void modify(PmsColumnNatureSaveVo pmsColumnNatureSaveVo);

    /**
     * @description 禁用通用分类
     * @param ids
     * @return
     */
    void disable(String ids);

    /**
     * @description 查询当前分类下的所有有效通用属性
     * @param id
     * @return
     */
    PageInfo<PmsColumnNatureDto> queryByColumnId(Long id,Long pageNo,Long pageSize);
}

