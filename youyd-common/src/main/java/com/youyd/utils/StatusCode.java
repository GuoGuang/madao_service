package com.youyd.utils;

/**
 * @description: 状态码定义
 * @author: LGG
 * @create: 2018-09-26 14:34
 **/
public enum StatusCode {
	OK(20000,"操作成功"), //成功
	ERROR(20001,"操作失败"), // error
	SYSTEM_EXCEPTION(0,"系统异常"), // 系统异常
	LOGIN_ERROR(20002,"用户名或密码错误"),// Login error
	ACCESS_ERROR(20003,"权限不足"),// 权限不足 Access error
	RPC_ERROR(20004,"远程调用失败"),// 远程调用失败 Remote Procedure Call error
	PARAM_ERROR(20006,"参数不正确");

	private Integer code;
	private String msg;
	public Integer getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	StatusCode() {
	}
	// 枚举中使用的值需要在构造函数中定义
	StatusCode(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}
}
