package com.liyuan.wave.sms.client;

import com.liyuan.wave.common.client.WaveServiceList;
import com.liyuan.wave.common.vo.response.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-20-0:27
 */
@Component
@FeignClient(value = WaveServiceList.WAVE_SERVICE_PMS)
public interface PmsProductSkuClient {

    /**
     * @description 商品sku对应商品的信息
     * @param articleNumber
     * @return
     */
    @GetMapping("pms/product/sku")
    JsonResult getDetailByArticleNumber(@RequestParam(name = "articleNumber") String articleNumber);
}
