package com.langchao.wavecommon.exception;

public class ExceptionCast {
    public static void cast(ExceptionResult exceptionResult){
        throw new ExceptionToExtends(exceptionResult);
    }
}