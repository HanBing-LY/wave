package com.langchao.waveservicemall.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.langchao.waveservicemall.pojo.base.BasicPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author
 * @Title: UserBank
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:12 CST 2019
 */
@Data
@TableName("user_bank")
@NoArgsConstructor
@AllArgsConstructor
public class UserBank extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行账号
     */
    private String bankAccount;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 户名
     */
    private String userName;

}

