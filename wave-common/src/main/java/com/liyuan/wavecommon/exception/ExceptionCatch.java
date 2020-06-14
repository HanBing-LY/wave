package com.liyuan.wavecommon.exception;

import com.liyuan.wavecommon.vo.response.JsonResult;
import com.liyuan.wavecommon.vo.response.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
@Slf4j
public class ExceptionCatch {

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public JsonResult exception(CommonException commonException){
        ExceptionResult result= commonException.getExceptionResult();
        log.error("exception:", commonException.getMessage());
        return new JsonResult(result.success(), result.code(),result.message());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult exception(Exception ex){
        log.error("exception:",ex.getMessage());
        return new JsonResult(false, ResultStatus.EXCEPTION_FAIL,ex.getMessage());
    }
}
