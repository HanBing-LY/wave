package com.langchao.waveserviceuser.controller;

import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavecommon.web.BaseController;
import com.langchao.wavepo.user.User;
import com.langchao.wavepo.user.UserVo;
import com.langchao.waveserviceapi.user.UserControllerApi;
import com.langchao.waveserviceuser.clients.SearchClient;
import com.langchao.waveserviceuser.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2020/2/2.
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController implements UserControllerApi {
	@Resource
	private UserService userService;
	@Resource
	private SearchClient searchClient;

	@Override
	@GetMapping("/list")
	public JsonResult findUserList(@RequestBody UserVo userVo) {
//		return success(userService.findUserList(userVo));
		//调用搜索引擎
		return searchClient.list(userVo);
	}

	@Override
	@PostMapping("/")
	@HystrixCommand(fallbackMethod = "dead")//对当前方法加熔断
	public JsonResult addUser(@RequestBody User user) {
		userService.addUser(user);
		return success();
	}

	@Override
	@GetMapping("/{userId}")
	public JsonResult getUserById(@PathVariable String userId) {
		return success(userService.getById(userId));
	}

	@Override
	@PutMapping("/")
	public JsonResult updateUser(@RequestBody User user) {
		return success(userService.updateUser(user));
	}

	@Override
	@PostMapping("/pictures/")
	public JsonResult addUserPic(@RequestParam("userId") String userId, @RequestParam("pic") String pic) {//pic是文件服务器调用storage后返回的地址
		userService.addUserPic(userId,pic);
		return success();
	}

	@Override
	@GetMapping("/pictures/list/{userId}")
	public JsonResult findUserPic(@PathVariable String userId) {
		return success(userService.getById(userId));
	}

	@Override
	@DeleteMapping("/pictures/{userId}")
	public JsonResult deleteUserPic(@PathVariable String userId) {
		userService.deleteUserPic(userId);
		return success();
	}

	@Override
	@PostMapping("/pages/{userId}")
	public JsonResult preview(@PathVariable String userId) {
		return success(userService.preview(userId));
	}

	@Override
	@PostMapping("/publish/{userId}")
	public JsonResult publish(@PathVariable String userId) {
		return success("success",userService.publish(userId));
	}

}
