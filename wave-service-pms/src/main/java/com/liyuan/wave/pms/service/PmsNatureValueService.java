package com.liyuan.wave.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.pms.po.dto.PmsNatureValueDto;
import com.liyuan.wave.pms.po.vo.PmsNatureValueSaveVo;
import com.liyuan.wave.po.pms.PmsNatureValue;
import com.liyuan.wave.common.vo.response.PageInfo;

/**
 * @description pms_nature_value
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
public interface PmsNatureValueService extends IService<PmsNatureValue> {
    void add(PmsNatureValueSaveVo pmsNatureValueVo);

    void modify(PmsNatureValueSaveVo pmsNatureValueVo);

    void disable(String ids);

    PageInfo<PmsNatureValueDto> queryByColumnNatureId(Long id, Long pageNo, Long pageSize);
}

