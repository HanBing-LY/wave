package com.langchao.waveservicemall.service.impl;

import com.chemguan.consumerservice.ClubInfoConsumerService;
import com.chemguan.consumerservice.UserConsumerService;
import com.chemguan.dao.ProductInfoMapper;
import com.chemguan.entity.ClubInfo;
import com.chemguan.entity.UserInfo;
import com.chemguan.service.UserScoreService;
import com.chemguan.vo.UserScoreVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserScoreServiceImpl  implements UserScoreService {

    @Autowired
    private UserConsumerService userConsumerService;

    @Autowired
    private ClubInfoConsumerService clubInfoConsumerService;

    @Autowired
    private ProductInfoMapper productInfoMapper;
    /**
     * @Author ws
     * @Description 根据用户id 查询用户积分相关信息
     * @param userId 用户id
     * @return
     */
    @Override
    public UserScoreVo getUserScoreInfo(Integer userId) {
        //创建一个返回对象
        UserScoreVo userScoreVo = new UserScoreVo();
        UserInfo userInfo = userConsumerService.getUsers(userId);
        if (userInfo != null) {
            BeanUtils.copyProperties(userInfo, userScoreVo);
            //是否vip用户
            if(userInfo.getContributeMoney()!=null){
                userScoreVo.setIsVipUser(userInfo.getContributeMoney() >= 5000);
            }
            userScoreVo.setUserContributeMoney(userInfo.getContributeMoney());
            //是否为资深俱乐部负责人
            ClubInfo clubInfo = clubInfoConsumerService.findByUserId(userId);
            if (clubInfo != null) {
                //暂时只判断俱乐部消费  后期有条件再增加
                userScoreVo.setIsVipClubLeader(clubInfo.getConsumMoney() >= 30000);
                userScoreVo.setClubConsumMoney(clubInfo.getConsumMoney());
            } else {
                userScoreVo.setIsVipClubLeader(false);
            }
        }else{
            //用户不存在 全局异常处理
        }
        return userScoreVo;
    }



}

