package com.codeway.enums;

/**
 * 状态码定义
 **/
public enum StatusEnum {

	/* 系统ERROR */
	UNKNOWN(-10001,"系统错误"),
	EXCEEDED_FILE_SIZE_LIMIT(-10002,"超出文件大小限制"),


	OK(20000,"操作成功！"), //成功
	ERROR(20001,"操作失败！"), // error
	RPC_ERROR(20004,"远程调用失败！"),// 远程调用失败 Remote Procedure Call error
	PARAM_ILLEGAL(20006,"参数不正确！"),
	PARAM_MISSING(20007,"缺少请求参数！"),
	PARAM_INVALID(20008,"参数校验失败！"),
	REQUEST_ERROR(20009,"请求格式不匹配，请检查RestFul请求格式！"),
	SERVICE_OFF(20011,"服务已下线或未注册！"),
	SYSTEM_ERROR(50000,"系统异常！"),


	/* 认证，权限 */
	LOGIN_EXPIRED(40000, "登录已失效，请重新登录！"),
	INVALID_REQUEST(40001, "无效请求"),
	INVALID_CLIENT(40002, "无效client_id"),
	INVALID_GRANT(40003, "无效授权"),
	INVALID_SCOPE(40004, "无效scope"),
	INVALID_TOKEN(40005, "无效token"),
	UN_AUTHORIZED(40006, "权限不足！"),// 权限不足 Access error
	REDIRECT_URI_MISMATCH(40020, "redirect url不匹配"),
	ACCESS_DENIED(40030, "不存在的URL，拒绝访问"),
	METHOD_NOT_ALLOWED(40040, "不支持该方法"),
	SERVER_ERROR(40050, "权限服务错误"),
	UNAUTHORIZED_CLIENT(40060, "未授权客户端"),
	UNAUTHORIZED(40061, "未授权"),
	UNSUPPORTED_RESPONSE_TYPE(40070, " 支持的响应类型"),
	UNSUPPORTED_GRANT_TYPE(40071, "不支持的授权类型"),

	WRONG_PASSWORD(10001, "登录名或登录密码不正确！"),
	NICKNAME_REPEAT(10002, "该昵称已被他人使用！"),
	ERROR_CODE(10003, "验证码错误！"),
	SMS_SEND_ERROR(10003, "发送验证码失败！"),
	FAILED_TOO_MANY(10004, "登录失败次数太多！"),
	LOGIN_ERROR(20002, "用户名或密码错误！"),
	TWICE_PASSWORD_NOT_MATCH(10004, "两次输入的密码不匹配！");


	private Integer code;
	private String msg;

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	StatusEnum() {
	}
	// 枚举中使用的值需要在构造函数中定义
	StatusEnum(Integer code, String msg){
		this.code = code;
		this.msg = msg;
	}
}
