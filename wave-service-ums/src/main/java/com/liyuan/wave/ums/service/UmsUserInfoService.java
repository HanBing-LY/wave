package com.liyuan.wave.ums.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.po.ums.UmsUserInfo;
import com.liyuan.wave.po.ums.vo.UmsUserInfoScoreVo;

/**
 * @author liyuan
 * @description
 * @date 2020-06-15 15:46
 */
public interface UmsUserInfoService extends IService<UmsUserInfo> {

    /**
     * @description 添加用户积分
     * @param umsUserInfoScoreVo
     */
    void incrementScore(UmsUserInfoScoreVo umsUserInfoScoreVo);
}
