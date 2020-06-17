package com.liyuan.wavecommon.web;

import com.liyuan.wavecommon.vo.response.JsonResult;
import com.liyuan.wavecommon.vo.response.ResultStatus;

/**
 * @author liyuan
 * @description controller层http相应 ;所有的失败相应必须通过全局异常去响应
 * @date 2020-06-15 15:47
 */
public abstract class BaseController {

    protected JsonResult success() {
        return new JsonResult(true, ResultStatus.SUCCESS, "操作成功", null);
    }

    protected JsonResult success(Object data) {
        return new JsonResult(true, ResultStatus.SUCCESS, "操作成功", data);
    }

    protected JsonResult success(String message, Object data) {
        return new JsonResult(true, ResultStatus.SUCCESS, message, data);
    }

}
