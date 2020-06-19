package com.liyuan.wave.sms.util;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-19-22:23
 */
public class CheckTimeUtil {

    /**
     * @param start
     * @param end
     * @return
     * @description 判断时间是否有效, 活动提前一小时, 开始时间大于结束时间
     */
    public static boolean judgeStartTimeIsEffective(Date start, Date end) {
        int compare = DateUtil.compare(start, end);
        return compare < 0;
    }
}
