package com.langchao.wavecommon.exception;

public interface ExceptionResult {
    //操作是否成功,true为成功，false操作失败
    boolean success();
    //操作相应编码
    int code();
    //提示信息
    String message();
}