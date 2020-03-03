package com.langchao.waveserviceapi.user;


import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavepo.user.User;
import com.langchao.wavepo.user.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户管理接口", tags = "用户管理接口")
public interface UserControllerApi {

	@ApiOperation("查询用户列表")
	public JsonResult findUserList(UserVo userVo);

	@ApiOperation("添加用户信息")
	public JsonResult addUser(User user);

	@ApiOperation("获取用户信息")
	public JsonResult getUserById(String userId);

	@ApiOperation("更新用户信息")
	public JsonResult updateUser(User user);

	@ApiOperation("添加用户图片")
	public JsonResult addUserPic(String userId, String pic);

	@ApiOperation("查询用户图片")
	public JsonResult findUserPic(String userId);

	@ApiOperation("删除用户图片")
	public JsonResult deleteUserPic(String userId);

	@ApiOperation("预览用户")
	public JsonResult preview(String userId);

	@ApiOperation("发布用户")
	public JsonResult publish(String userId);

}
