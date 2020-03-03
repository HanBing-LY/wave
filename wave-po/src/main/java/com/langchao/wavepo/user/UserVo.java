package com.langchao.wavepo.user;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo extends User implements Serializable {

	private Integer pageNumber;

	private Integer pageSize;

	private String orderColumn ="id";
	//排序方式
	private String orderStyle ="desc";
}
