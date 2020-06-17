package com.liyuan.wave.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.pms.po.dto.PmsColumnNatureDto;
import com.liyuan.wave.pms.po.vo.PmsColumnNatureVo;
import com.liyuan.wave.po.pms.PmsColumnNature;
import com.liyuan.wavecommon.vo.response.PageInfo;

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
     * @param pmsColumnNatureDto
     * @return
     */
    void add(PmsColumnNatureDto pmsColumnNatureDto);

    /**
     * @description 修改分类属性名称
     * @param pmsColumnNature
     * @return
     */
    void modify(PmsColumnNature pmsColumnNature);

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
    PageInfo<PmsColumnNatureVo> queryByColumnId(String id);
}

