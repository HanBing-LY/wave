package com.liyuan.wave.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.common.exception.ExceptionCast;
import com.liyuan.wave.po.ums.UmsUserInfo;
import com.liyuan.wave.po.ums.vo.UmsUserInfoScoreVo;
import com.liyuan.wave.ums.common.UmsExceptionCode;
import com.liyuan.wave.ums.mapper.UmsUserInfoMapper;
import com.liyuan.wave.ums.service.UmsUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liyuan
 * @description
 * @date 2020-06-15 15:46
 */
@Service
public class UmsUserInfoServiceImpl extends ServiceImpl<UmsUserInfoMapper, UmsUserInfo> implements UmsUserInfoService {

    @Autowired
    private UmsUserInfoMapper umsUserInfoMapper;

    /**
     * @description 添加用户积分
     * @param umsUserInfoScoreVo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void incrementScore(UmsUserInfoScoreVo umsUserInfoScoreVo) {
        Long id = umsUserInfoScoreVo.getId();
        UmsUserInfo umsUserInfo = umsUserInfoMapper.selectById(id);
        Date updateTime = umsUserInfo.getUpdateTime();
        Long userScore = umsUserInfo.getUserScore();
        BigDecimal contributeMoney = umsUserInfo.getContributeMoney();
        userScore = userScore + umsUserInfoScoreVo.getUserScore();
        contributeMoney = contributeMoney.add(umsUserInfoScoreVo.getContributeMoney());
        umsUserInfo = new UmsUserInfo();
        umsUserInfo.setUserScore(userScore);
        umsUserInfo.setContributeMoney(contributeMoney);
        // 乐观锁校验
        QueryWrapper<UmsUserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        queryWrapper.eq("update_time",updateTime);
        int update = umsUserInfoMapper.update(umsUserInfo, queryWrapper);
        if(update != 1){
            ExceptionCast.cast(UmsExceptionCode.SCORE_INCREMENT_FAIL);
        }
    }
}
