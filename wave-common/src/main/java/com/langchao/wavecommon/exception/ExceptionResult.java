package com.langchao.wavecommon.exception;

public interface ExceptionResult {

    boolean success();

    int code();

    String message();
}