package com.langchao.waveservicemall.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDto extends OrderInfo {

    /** 用户名称 */
    private String userName;

}
