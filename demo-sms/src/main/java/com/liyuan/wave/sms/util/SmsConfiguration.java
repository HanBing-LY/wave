package com.liyuan.wave.sms.util;

/**
 * @author liyuan
 * @description
 * @date 2020-06-20 16:38
 */
public interface SmsConfiguration {


    /**
     * 短信API调用成功的结果
     */
    static final String IT_SUCCESS = "OK";

    /**
     * 短信API产品名称（短信产品名固定，无需修改）
     */
    static final String PRODUCT_NAME = "Dysmsapi";

    /**
     * 短信API产品域名（接口地址固定，无需修改）
     */
    static final String DOMAIN_NAME = "dysmsapi.aliyuncs.com";

    /**
     * 设置超时时间-可自行调整
     */
    static final String DEFAULT_CONNECT_TIMEOUT = "10000";

    /**
     * 设置超时时间-可自行调整
     */
    static final String DEFAULT_READ_TIMEOUT = "10000";

    /**
     * accessKeyId(必须)
     */

    static final String ACCESSKEY_ID = "LTAI4GKzQV1Rv97kmFEtcdCu";

    /**
     * accessKeySecret(必须)
     */
    static final String ACCESSKEY_SECRET = "0thvb6cFeEmqPYO7uQrQfHP2QRa5nE";

    /**
     * 签名(必须)
     */
    static final String SIGN_NAME = "ishop绑定手机验证";

    /**
     * 模版(必须)
     */
    static final String TEMPLATE_CODE = "SMS_187751117";

    /**
     * @return 返回值
     * @description 默认方法, 6位验证码
     */
    default String giveMeCode() {
        return Integer.toString((int) ((Math.random() * 9 + 1) * 100000));
    }
}
