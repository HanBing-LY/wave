package com.langchao.waveservicemall.pojo.dto;

import com.langchao.waveservicemall.pojo.FlashSaleProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author liyuan
 * @create 2020-03-20-18:23-周五
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlashSaleProductDTO extends FlashSaleProduct {

    /** 结束时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /** 秒杀剩余时间 */
    private Long toEndTime;

}
