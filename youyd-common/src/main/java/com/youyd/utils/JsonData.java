package com.youyd.utils;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * 通用json返回类型
 * @author Administrator
 * @param <E>
 *
 */

@ApiModel("api接口通用返回对象")
public class JsonData implements Serializable {

	private boolean status;

	private int code;

	private String message;

	private Object data;

	public JsonData(boolean status, int code, String message, Object data) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public JsonData(boolean state, Integer code, String msg) {
		this.status = state;
		this.code = code;
		this.message = msg;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
