package com.liyuan.wavecommon.vo.response;

import com.liyuan.wavecommon.exception.ExceptionResult;


public enum CommonCode  implements ExceptionResult {

    SUCCESS(true,200,"操作成功！"),
    FAIL(false,000,"操作失败！");

    boolean success;

    int code;

    String message;

    private CommonCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }
    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}
