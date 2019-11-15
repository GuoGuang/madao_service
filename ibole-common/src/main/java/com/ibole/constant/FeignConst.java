package com.ibole.constant;


/**
 * 定义一组Feign常量;
 * @author : LGG
 * @create : 2019-05-04 14:57
 **/
public final class FeignConst {

	private FeignConst() {
		throw new IllegalStateException("Utility class");
	}

	/* 基础微服务 */
	public static final String SERVICE_BASE  = "service-base";
	public static final String SERVICE_BASE_PATH  = "/loginLog";

	/* 用户微服务 */
	public static final String SERVICE_USER  = "service-user";
	public static final String SERVICE_USER_PATH  = "/su/user";
	public static final String SERVICE_RESOURCE_PATH  = "/su/resource";

	/* 认证微服务 */
	public static final String SERVICE_AUTHENTICATION_AUTH  = "authentication-server";
	public static final String SERVICE_AUTHENTICATION_AUTH_PATH  = "/";

	/* 授权微服务 */
	public static final String SERVICE_AUTHORIZATION_AUTH  = "authorization-server";
	public static final String SERVICE_AUTHORIZATION_AUTH_PATH  = "/";
}
