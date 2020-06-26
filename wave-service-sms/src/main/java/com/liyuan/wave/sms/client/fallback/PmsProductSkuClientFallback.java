package com.liyuan.wave.sms.client.fallback;

import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.sms.client.PmsProductSkuClient;
import org.springframework.stereotype.Component;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-20-0:27
 */
@Component
public class PmsProductSkuClientFallback implements PmsProductSkuClient {

    @Override
    public JsonResult getDetailByArticleNumber(String articleNumber) {
        return JsonResult.busying();
    }
}
