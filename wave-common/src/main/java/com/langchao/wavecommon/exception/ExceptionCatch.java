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

    @ExceptionHandler(ExceptionToExtends.class)
    @ResponseBody
    public JsonResult exception(ExceptionToExtends exceptionToExtends){
        ExceptionResult result= exceptionToExtends.getExceptionResult();
        log.error("exception:",exceptionToExtends.getMessage());
        return new JsonResult(result.success(), result.code(),result.message());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult exception(Exception ex){
        log.error("exception:",ex.getMessage());
        return new JsonResult(false, ResultStatus.EXCEPTION_FAIL,ex.getMessage());
    }
}
