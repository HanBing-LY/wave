package com.liyuan.wave.pms.common;

import com.liyuan.wave.common.exception.ExceptionResult;
import lombok.ToString;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-18-0:15
 */
@ToString
public enum PmsProductColumnCode implements ExceptionResult {

    PARENT_NOT_EXIST(false,90001,"该父节点不存在,请重选！"),
    PLEASE_CHO0SE_TO_DELETE(false,90002,"请选择需要删除的分类！")


    ;

    //操作代码
    boolean success;

    //操作代码
    int code;
    //提示信息
    String message;

    PmsProductColumnCode(boolean success, int code, String message){
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
