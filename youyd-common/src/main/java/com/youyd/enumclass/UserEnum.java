package com.youyd.enumclass;


/**
 * <p>用户模块 状态相关枚举</p>
 * @author LGG
 * @create 2019年4月23日22:35:18
 * @version 1.0.0
 */
public enum UserEnum {

	WRONG_PASSWORD(10001,"密码错误，请重试！");

	private Integer code;

	private String info;

	UserEnum(Integer code, String info) {
		this.code = code;
		this.info = info;
	}

	public Integer getCode() {
		return code;
	}

	public String getInfo() {
		return info;
	}

}
