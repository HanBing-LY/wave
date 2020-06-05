package com.langchao.waveservicemall.service;


import com.langchao.waveservicemall.pojo.vo.UserScoreVo;

public interface UserScoreService {

    //获取用户积分信息 是否vip 资深负责人
    UserScoreVo getUserScoreInfo(Integer userId);


}
