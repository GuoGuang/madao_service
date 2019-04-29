package com.youyd.enums;


import lombok.Getter;

/**
 * <p>用户模块 状态相关枚举</p>
 * @author LGG
 * @create 2019年4月23日22:35:18
 * @version 1.0.0
 */
@Getter
public enum UserEnum {

	WRONG_PASSWORD(10001,"登录名或登录密码不正确！"),
	NICKNAME_REPEAT(10002,"该昵称已被他人使用！"),
	ERROR_CODE(10003,"验证码错误！"),
	FAILED_TOO_MANY(10004,"登录失败次数太多！");


	private Integer code;

	private String info;

	UserEnum(Integer code, String info) {
		this.code = code;
		this.info = info;
	}


}
