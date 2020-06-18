package com.liyuan.wave.common.exception;

public class ExceptionCast {
    public static void cast(ExceptionResult exceptionResult){
         throw new CommonException(exceptionResult);
    }
}