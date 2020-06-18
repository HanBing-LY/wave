package com.liyuan.wave.common.exception;

public interface ExceptionResult {

    boolean success();

    int code();

    String message();
}