package com.liyuan.wavecommon.exception;

public interface ExceptionResult {

    boolean success();

    int code();

    String message();
}