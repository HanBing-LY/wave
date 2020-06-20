package com.liyuan.wave.sms.mapper;

import com.liyuan.wave.po.sms.SmsGroupInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyuan.wave.sms.po.dto.SmsGroupSaleProductDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description sms_group_info
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Mapper
public interface SmsGroupInfoMapper extends BaseMapper<SmsGroupInfo> {

    /**
     * @description 商品货号查拼团商品
     * @param articleNumber
     * @return
     */
    SmsGroupSaleProductDto selectByArticleNumber(@Param("articleNumber") String articleNumber);
}
