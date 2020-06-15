package com.liyuan.wave.usercenter.controller;


import com.liyuan.wave.usercenter.clients.SearchClient;
import com.liyuan.wave.usercenter.service.UserService;
import com.liyuan.wave.po.ucenter.UserVo;
import com.liyuan.wavecommon.vo.response.JsonResult;
import com.liyuan.wavecommon.web.BaseController;
import com.liyuan.waveserviceapi.user.UserControllerApi;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
		return searchClient.list(userVo);
	}

	/**
	 * 静态化预览界面(超时访问,开启降级5s)
	 * @param userId
	 * @param response
	 * @return
	 */
	@Override
	@PostMapping("/pages/{userId}")
	@HystrixCommand(fallbackMethod = "dead",commandProperties = @HystrixProperty(name = "execution.isolation.com.liyuan.wave.thread.timeoutInMilliseconds",value = "5000"))
	public JsonResult preview(@PathVariable String userId, HttpServletResponse response) {
		String html = userService.getHtml(userId);
		response.addHeader("content-type","text/html;charset=utf-8");
		response.setHeader("content-type","text/html;charset=utf-8");
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			outputStream.write(html.getBytes("utf-8"));
		} catch (IOException e) {

		}
		return success();
	}

	private String dead(){
		return "请求超时;当前线程池名字" + Thread.currentThread().getName();
	}

	@Override
	@PostMapping("/publish/{userId}")
	@HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),              //是否开启断路器
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),    //请求数达到后才计算
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),  //错误率达到多少跳闸
	})
	public JsonResult publish(@PathVariable String userId) {
		return success("success",userService.publish(userId));
	}

	private String paymentCircuitBreakerFallback(){
		return "系统正忙,请稍候再试";
	}

}
