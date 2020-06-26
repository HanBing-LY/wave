package com.liyuan.wave.common.vo.response;

import com.liyuan.wave.common.exception.ExceptionResult;
import lombok.NonNull;

import java.util.Objects;

/**
 * @author liyuan
 * @description json相应
 * @date 2019-12-15 15:47
 */
public class JsonResult {

    @NonNull
    private boolean success;
    @NonNull
    private int status;
    @NonNull
    private String message;

    private Object data;


    public JsonResult() {
    }

    public JsonResult(@NonNull boolean success, @NonNull int status, @NonNull String message) {
        this.success = success;
        this.status = status;
        this.message = message;
    }

    public JsonResult(@NonNull boolean success, @NonNull int status, @NonNull String message, Object data) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    @Override
    public int hashCode() {
        return Objects.hash(isSuccess(), getStatus(), getMessage(), getData());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JsonResult)) {
            return false;
        }
        JsonResult that = (JsonResult) o;
        return isSuccess() == that.isSuccess() &&
                getStatus() == that.getStatus() &&
                getMessage().equals(that.getMessage()) &&
                getData().equals(that.getData());
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "success=" + success +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }



    public JsonResult(ExceptionResult exceptionResult) {
        this.success = exceptionResult.success();
        this.status = exceptionResult.code();
        this.message = exceptionResult.message();
    }

    public static JsonResult busying() {
        return new JsonResult(CommonCode.BUSYING);
    }

}
