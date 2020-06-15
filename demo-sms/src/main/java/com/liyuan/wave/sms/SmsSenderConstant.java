package com.liyuan.wave.sms;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-15-23:25
 */
public interface SmsSenderConstant {

    static final String DEFAULT_CONNECT_TIMEOUT = "10000";

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

}
