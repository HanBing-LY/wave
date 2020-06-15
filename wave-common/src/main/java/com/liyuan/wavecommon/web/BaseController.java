package com.liyuan.wavecommon.web;

import com.liyuan.wavecommon.vo.response.JsonResult;
import com.liyuan.wavecommon.vo.response.ResultStatus;

/**
 * @author liyuan
 * @description controller层http相应
 * @date 2020-06-15 15:47
 */
public class BaseController {

    public JsonResult success(String message, Object data) {
        return new JsonResult(true, ResultStatus.SUCCESS, message, data);
    }

    public JsonResult success(String message) {
        return new JsonResult(true, ResultStatus.SUCCESS, message, null);
    }

    public JsonResult success(Object data) {
        return new JsonResult(true, ResultStatus.SUCCESS, "操作成功", data);
    }

    public JsonResult success() {
        return new JsonResult(true, ResultStatus.SUCCESS, "操作成功", null);
    }

}
