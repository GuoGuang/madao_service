package com.ibole.enums;


import lombok.Getter;

/**
 * <p>用户模块 状态相关枚举</p>
 * @version 1.0.0
 */
@Getter
public enum UserEnum {

	WRONG_PASSWORD(10001,"登录名或登录密码不正确！"),
	NICKNAME_REPEAT(10002,"该昵称已被他人使用！"),
	ERROR_CODE(10003,"验证码错误！"),
	FAILED_TOO_MANY(10004,"登录失败次数太多！"),
	TWICE_PASSWORD_NOT_MATCH(10004,"两次输入的密码不匹配！");
	private Integer code;

	private String info;

	UserEnum(Integer code, String info) {
		this.code = code;
		this.info = info;
	}


}