package com.langchao.wavepo.user;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {
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
