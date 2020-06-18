package com.liyuan.wave.auth.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liyuan.wave.po.ucenter.ext.AuthToken;
import com.liyuan.wave.po.ucenter.response.AuthCode;
import com.liyuan.wave.common.exception.ExceptionCast;
import com.liyuan.wave.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {


    @Value("${auth.tokenValiditySeconds}")
    private long tokenValiditySeconds;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    //用户认证申请令牌，将令牌存储到redis
    public AuthToken login(String username, String password, String clientId, String clientSecret) {
        AuthToken authToken = this.applyToken(username, password, clientId, clientSecret);
        if(authToken==null){
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_APPLYTOKEN_FAIL);
        }
        //短令牌
        String accessToken = authToken.getAccess_token();
        ObjectMapper objectMapper = new ObjectMapper();
        //完全令牌
        try {
            String tokenStr = objectMapper.writeValueAsString(authToken);

            //将令牌存储到redis
            boolean result = this.saveToken(accessToken, tokenStr, tokenValiditySeconds);
            if(!result){
                ExceptionCast.cast(AuthCode.AUTH_LOGIN_APPLYTOKEN_FAIL);
            }
        } catch (JsonProcessingException e) {
           ExceptionCast.cast(AuthCode.AUTH_LOGIN_APPLYTOKEN_FAIL);
        }
        return authToken;


    }

    /**
     * 保存token到redis
     * @param accessToken 短令牌
     * @param tokenStr 完整令牌
     * @param tokenValiditySeconds 有效期
     * @return
     */
    private boolean saveToken(String accessToken, String tokenStr, long tokenValiditySeconds) {
        String key = "user_token:"+accessToken;
        stringRedisTemplate.opsForValue().set(key,tokenStr,tokenValiditySeconds, TimeUnit.SECONDS);
        return stringRedisTemplate.getExpire(key)>0;
    }

    //获取httpbasic的串
    private String getHttpBasic(String clientId,String clientSecret){
        String string = clientId+":"+clientSecret;
        //将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic "+new String(encode);
    }

    //申请令牌
    private AuthToken applyToken(String username, String password, String clientId, String clientSecret) {
        //请求地址
        String authUrl = "http://localhost:40400/oauth/token";
        //定义header
        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        String httpBasic = getHttpBasic(clientId, clientSecret);
        header.add("Authorization",httpBasic);

        //定义body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type","password");
        body.add("username",username);
        body.add("password",password);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
        //设置restTemplate远程调用时候，对400和401不让报错，正确返回数据
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if(response.getRawStatusCode()!=400 && response.getRawStatusCode()!=401){
                    super.handleError(response);
                }
            }
        });

        ResponseEntity<Map> exchange=null;
        try {
           exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);
        }catch(Exception e){
            ExceptionCast.cast(AuthCode.AUTH_ACCOUNT_NOTEXISTS);
        }
        //申请令牌信息
        Map bodyMap = exchange.getBody();

        String accessToken = (String) bodyMap.get("accessToken");
        String jti = (String) bodyMap.get("jti");
        String refreshToken = (String) bodyMap.get("refreshToken");
        if(bodyMap.isEmpty()
                || StringUtils.isEmpty(jti)
                || StringUtils.isEmpty(accessToken)
                || StringUtils.isEmpty(refreshToken) ){
            //解析spring security返回的错误信息
            if(bodyMap.get("error_description") != null){
                String errorDescription = (String) bodyMap.get("errorDescription");
                if(errorDescription.contains("Bad credentials")){
                    ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
                }
            }


            return null;
        }

        AuthToken authToken =new AuthToken();
        authToken.setAccess_token(jti);
        authToken.setJwt_token(accessToken);
        authToken.setRefresh_token(refreshToken);
        return authToken;
    }


    public AuthToken getJwtFromRedis(String access_token) {
        String key = "user_token:"+access_token;
        //从redis获取令牌字符串
        String tokenStr = stringRedisTemplate.opsForValue().get(key);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //转AuthToken对象
            return objectMapper.readValue(tokenStr, AuthToken.class);
        } catch (IOException e) {
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_ERROR);
            return null;
        }
    }

    public void delTokenFromRedis(String access_token) {
        String key = "user_token:"+access_token;
        stringRedisTemplate.delete(key);
    }
}
