package com.liyuan.wave.ums.common;

import com.liyuan.wave.common.exception.ExceptionResult;
import lombok.ToString;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-18-0:15
 */
@ToString
public enum UmsExceptionCode implements ExceptionResult {

    ADDRESS_UPDATE_ERROR(false,90301,"地址修改失败！"),
    PLEASE_CHO0SE_TO_DELETE(false,90302,"请选择需要删除的地址！"),
    COMMON_ADDRESS_NOT_TO_DELETE(false,90303,"请选择需要删除的地址！"),
    SCORE_INCREMENT_FAIL(false,90304,"积分添加失败！"),

    ;

    //操作代码
    boolean success;

    //操作代码
    int code;
    //提示信息
    String message;

    UmsExceptionCode(boolean success, int code, String message){
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
