package com.liyuan.wave.sms.common;

import com.liyuan.wave.common.exception.ExceptionResult;
import lombok.ToString;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-18-0:15
 */
@ToString
public enum SmsExceptionCode implements ExceptionResult {

    TIME_NOT_TRUE(false,90101,"请输入正确的活动时间！"),
    PLEASE_CHO0SE_TO_DELETE(false,90102,"请选择需要停止的秒杀时间段！"),
    FLASH_SALE_PRICE_ERROR(false,90103,"秒杀价必须低于售价！"),
    GROUP_SALE_PRICE_ERROR(false,90103,"拼团价必须低于售价！")


    ;

    //操作代码
    boolean success;

    //操作代码
    int code;
    //提示信息
    String message;

    SmsExceptionCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return false;
    }

    @Override
    public int code() {
        return 0;
    }

    @Override
    public String message() {
        return null;
    }
}
