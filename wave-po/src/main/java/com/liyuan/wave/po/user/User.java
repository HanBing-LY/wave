package com.liyuan.wave.po.user;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("user")
public class User implements Serializable {
	@TableId
	private Integer userId;
	private String loginName;
	private String userName;
	private String userType;
	private String email;
	private String phonenumber;
	private String sex;
	private String avatar;
	private String password;
	private String salt;
	private Integer status;
	private String delFlag;
	private String loginIp;
	private Date loginDate;
	private String createBy;
	private Date createTime;
	private String updateBy;
	private Date updateTime;
	private String remark;
}
