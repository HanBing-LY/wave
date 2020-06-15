package com.liyuan.waveserviceapi.user;


import com.liyuan.wave.po.ucenter.UserVo;
import com.liyuan.wavecommon.vo.response.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;

@Api(value = "用户管理接口", tags = "用户管理接口")
public interface UserControllerApi {

	@ApiOperation("查询用户列表")
	JsonResult findUserList(UserVo userVo);

	@ApiOperation("预览用户")
	JsonResult preview(String userId, HttpServletResponse response);

	@ApiOperation("发布用户")
	JsonResult publish(String userId);

}
