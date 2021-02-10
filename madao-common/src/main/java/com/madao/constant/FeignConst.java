package com.madao.constant;

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
