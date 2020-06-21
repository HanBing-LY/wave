package com.liyuan.wave.wms.common;

import com.liyuan.wave.common.exception.ExceptionResult;
import lombok.ToString;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-18-0:15
 */
@ToString
public enum WmsExceptionCode implements ExceptionResult {

    WARE_SKU_SUFFICIENT(false,90201,"商品库存不足！"),
    WARE_SKU_TO_DECREMENT_IS_ERROR(false,90202,"需扣减的库存错误！")


    ;

    //操作代码
    boolean success;

    //操作代码
    int code;
    //提示信息
    String message;

    WmsExceptionCode(boolean success, int code, String message){
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
