package com.youyd.pojo;

import java.io.Serializable;

/**
 * @description: 返回结果类
 * @author: LGG
 * @create: 2018-09-26 14:29
 **/
public class Result implements Serializable {
	private boolean status;//是否成功
	private Integer code;// 返回码
	private String message;//返回信息
	private Object data;// 返回数据

	public Result() {
	}

	public Result(boolean status, Integer code, String message, Object data) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public Result(boolean status, Integer code, String message) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
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
