package com.langchao.waveservicemall.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.langchao.waveservicemall.pojo.base.BasicPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @Title: GroupSale
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:10 CST 2019
 */
@Data
@TableName("group_sale")
@NoArgsConstructor
@AllArgsConstructor
public class GroupSale extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 1:开启 0:关闭
     */
    private Integer flag;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

}

