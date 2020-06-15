package com.liyuan.wave.auth.controller;


import com.liyuan.wave.auth.service.AuthService;
import com.liyuan.wave.po.ucenter.ext.AuthToken;
import com.liyuan.wave.po.ucenter.request.LoginRequest;
import com.liyuan.wavecommon.util.CookieUtil;
import com.liyuan.wavecommon.util.ServletUtils;
import com.liyuan.wavecommon.util.StringUtils;
import com.liyuan.wavecommon.vo.response.JsonResult;
import com.liyuan.wavecommon.web.BaseController;
import com.liyuan.waveserviceapi.auth.AuthControllerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequestMapping
@RestController
public class AuthController extends BaseController implements AuthControllerApi {

    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;

    @Autowired
    private AuthService authService;

    @Override
    @PostMapping("/userlogin")
    public JsonResult login(LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        //调用登录业务
        AuthToken authToken = authService.login(username, password, clientId, clientSecret);
        //向客户保存cookie(短令牌)
        String accessToken = authToken.getAccess_token();
        this.saveCookie(accessToken);
        return success("登录成功",accessToken);
    }

    private void saveCookie(String accessToken) {
        HttpServletResponse response= ServletUtils.getResponse();
        CookieUtil.addCookie(response,cookieDomain,"/","uid",accessToken,cookieMaxAge,false);
    }

    @Override
    @PostMapping("/userlogout")
    public JsonResult logout() {
        //取出cookie中的用户短令牌
        String accessToken = getFormCookie();
        //清除cookie
        this.clearCookie(accessToken);
        //删除redis
        authService.delTokenFromRedis(accessToken);
        return success();
    }

    private void clearCookie(String accessToken) {
        CookieUtil.addCookie(ServletUtils.getResponse(),cookieDomain,"/","uid",accessToken,0,false);
    }

    @Override
    @GetMapping("/userjwt")
    public JsonResult userjwt() {

        //取出cookie中的用户短令牌
        String accessToken = getFormCookie();

        if(StringUtils.isEmpty(accessToken)){
//            throw new CommonException(" ");
        }

        //根据短令牌从redis获得jwt
        AuthToken authToken =  authService.getJwtFromRedis(accessToken);
        return success("获取令牌成功",authToken.getJwt_token());
    }

    /**
     * 获取uid的cookie值
     * @return
     */
    private String getFormCookie() {
        Map<String, String> map = CookieUtil.readCookie(ServletUtils.getRequest(), "uid");
        if(map!=null && map.get("uid")!=null){
            String uid = map.get("uid");
            return uid;
        }
        return null;
    }


}
