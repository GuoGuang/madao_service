package com.madao.constant;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public final class FeignConst {

    private FeignConst() {
        throw new IllegalStateException("Utility class");
    }

    /* 基础微服务 */
    public static final String SERVICE_BASE = "service-base";
    public static final String SERVICE_BASE_LOGIN_LOG_PATH = "/loginLog";
    public static final String SERVICE_BASE_OPT_LOG_PATH = "/optLog";

    /* 用户微服务 */
    public static final String SERVICE_USER = "service-user";
    public static final String SERVICE_USER_PATH = "/user";
    public static final String SERVICE_BLOG_USER_PATH = "/api/su";
    public static final String SERVICE_RESOURCE_PATH = "/resource";

    /* 认证微服务&授权微服务 */
    public static final String SERVICE_AUTHENTICATION_AUTH = "authentication-server";
    public static final String SERVICE_AUTHENTICATION_AUTH_PATH = "/";

}
