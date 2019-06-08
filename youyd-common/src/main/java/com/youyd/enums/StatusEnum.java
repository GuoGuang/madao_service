package com.youyd.enums;

/**
 * 状态码定义
 * @author : LGG
 * @create : 2018-09-26 14:34
 **/
public enum StatusEnum {

	/* 系统ERROR */
	UNKNOWN(-1,"系统错误"),


	OK(20000,"操作成功！"), //成功
	ERROR(20001,"操作失败！"), // error
	SYSTEM_EXCEPTION(0,"系统异常！"), // 系统异常
	LOGIN_ERROR(20002,"用户名或密码错误！"),// Login error
	ACCESS_ERROR(20003,"权限不足！"),// 权限不足 Access error
	RPC_ERROR(20004,"远程调用失败！"),// 远程调用失败 Remote Procedure Call error
	PARAM_ILLEGAL(20006,"参数不正确！"),
	PARAM_MISSING(20007,"缺少请求参数！"),
	PARAM_INVALID(20008,"参数校验失败！"),
	REQUEST_ERROR(20009,"请求格式错误！"),
	SYSTEM_ERROR(20010,"系统异常！"),
	SERVICE_OFF(20010,"服务已下线或未注册！");

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
