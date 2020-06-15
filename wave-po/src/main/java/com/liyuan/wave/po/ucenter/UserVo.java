package com.liyuan.wave.po.ucenter;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo extends User implements Serializable {

	private Integer pageNumber;

	private Integer pageSize;

	private String orderColumn ="id";
	//排序方式
	private String orderStyle ="desc";
}
