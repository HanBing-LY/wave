package com.liyuan.wave.common.exception;

import com.alibaba.fastjson.JSONObject;
import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.vo.response.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;


@ControllerAdvice
@Slf4j
public class ExceptionCatch {

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public JsonResult exception(CommonException commonException){
        ExceptionResult result= commonException.getExceptionResult();
        log.error("exception:", commonException.getMessage());
        return new JsonResult(result);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult exception(Exception exception){
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            String requestUrl = request.getScheme()
                    + "://" + request.getServerName()
                    + ":" + request.getServerPort()
                    + request.getContextPath()
                    + request.getServletPath()
                    + (StringUtils.isBlank(request.getQueryString()) ? "" : ("?" + request.getQueryString()));

            Map<String, String[]> parameterMap = request.getParameterMap();
            String json = read(request);
            StringBuilder requestLog = new StringBuilder();
            requestLog.append("请求信息：").append("URL = {").append(requestUrl).append("},\t")
                    .append("请求方式 = {").append(request.getMethod()).append("},\t")
                    .append("请求IP = {").append(request.getRemoteAddr()).append("},\t")
                    .append("请求头信息 = {").append(JSONObject.toJSONString(parameterMap)).append("},\t")
                    .append("请求体信息 = {").append(json).append("}\t");
        }catch (Exception exception1){
            log.error("推送异常信息失败:{}",exception1.getMessage());
            log.error("原异常信息---------------------->>>>>>>>>>>>>>>>>>>>");
            exception.printStackTrace();
        }

        return new JsonResult(false, ResultStatus.EXCEPTION_FAIL,exception.getMessage());
    }

    /**
     * 读取请求体
     */
    public static String read(HttpServletRequest request) {
        ServletInputStream is = null;
        try {
            is = request.getInputStream();
        } catch (IOException ioException) {
            log.error("获取请求流信息失败:{}", ioException.getMessage());
            return null;
        }
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                line = new String(line.getBytes(), StandardCharsets.UTF_8);
                sb.append(line);
            }
        } catch (IOException ioException) {
            log.error("读取请求参数失败:{}", ioException.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ioException) {
                    log.error("流关闭失败:{}", ioException.getMessage());
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ioException) {
                    log.error("流关闭失败:{}", ioException.getMessage());
                }
            }
        }
        return sb.toString();
    }
}
