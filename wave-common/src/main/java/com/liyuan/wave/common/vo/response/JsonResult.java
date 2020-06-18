package com.liyuan.wave.common.vo.response;

import lombok.*;

/**
 * @author liyuan
 * @description json相应
 * @date 2020-06-15 15:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class JsonResult {
    @NonNull
    private boolean success;
    @NonNull
    private int status;
    @NonNull
    private String message;
    private Object data;



    public JsonResult(CommonCode commonCode) {
            this.success=commonCode.success;
            this.status =commonCode.code;
            this.message = commonCode.message;
    }
}
