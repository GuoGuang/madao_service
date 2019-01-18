package com.youyd.utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 通用json返回类型
 * @author Administrator
 * @param <E>
 *
 */
public class JSONData {

	// 执行状态
	private boolean status;
	//状态码
	private int code;
	//返回消息
	private String message;
	//返回的数据
	private Object data;

	public JSONData(boolean status, int code, String message, Object data) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public JSONData(boolean state, Integer code, String msg) {
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
