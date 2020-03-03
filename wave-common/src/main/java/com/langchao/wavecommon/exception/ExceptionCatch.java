package com.langchao.wavecommon.exception;

import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavecommon.vo.response.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
@Slf4j
public class ExceptionCatch {

    //处理所有业务异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public JsonResult exception(CustomException ex){
        ExceptionResult result= ex.getExceptionResult();
        //记录日志
        log.error("catch exception:{}",ex.getMessage());
        return new JsonResult(result.success(), result.code(),result.message());
    }

    //捕获Exception异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult exception(Exception ex){
        //记录日志
        log.error("catch exception:{}",ex.getMessage());
        return new JsonResult(false, ResultStatus.EXCEPTION_FAIL,ex.getMessage());
    }
}
