package com.langchao.wavecommon.web;

import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavecommon.vo.response.ResultStatus;

public class BaseController {

    public JsonResult success(String message, Object data){
        return new JsonResult(true, ResultStatus.SUCCESS,message,data);
    }

    public JsonResult success(String message){
        return new JsonResult(true, ResultStatus.SUCCESS,message,null);
    }

    public JsonResult success(Object data){
        return new JsonResult(true, ResultStatus.SUCCESS,"操作成功",data);
    }

    public JsonResult success(){
        return new JsonResult(true, ResultStatus.SUCCESS,"操作成功",null);
    }

}
